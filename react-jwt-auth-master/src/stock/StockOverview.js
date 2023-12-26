import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import ColumnSelector from "./ColumnSelector";
import Modal from "react-modal";
import ServerErrorDialog from "./ServerErrorDialog";

const StockOverview = ({ userId, symbol }) => {
  console.log(
    "StockOverview input: userId: " + userId + ",  symbol: " + symbol
  );

  const [stockOverviewInfo, setStockOverviewInfo] = useState({ data: {} });
  const [selectedColumns, setSelectedColumns] = useState([
    "id",
    "Symbol",
    "DividendPerShare",
    "DividendYield",
    "AnalystTargetPrice",
    "DividendDate",
    "ExDividendDate",
    "PERatio",
    "MarketCapitalization",
  ]);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (symbol) {
      loadStock(userId, symbol);
    }
  }, [userId, symbol]);

  useEffect(() => {
    if (
      stockOverviewInfo.data &&
      Object.keys(stockOverviewInfo.data).length > 0
    ) {
      setSelectedColumns(selectedColumns);
    }
  }, [stockOverviewInfo.data, selectedColumns]);

  const loadStock = async (userId, symbol) => {
    const functionName = "OVERVIEW";
    const backendUrl = `http://localhost:8080/stock?userId=${userId}&symbol=${symbol}&functionName=${functionName}`;

    try {
      const response = await fetch(backendUrl);

      if (!response.ok) {
        console.log(`HTTP error! Status: ${response.status}`);
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const stockData = await response.json();

      if (Object.prototype.hasOwnProperty.call(stockData, "ErrorFromService")) {
        const errorValue = stockData.ErrorFromService;
        setError(errorValue);
      } else {
        setStockOverviewInfo({ data: stockData });

        // Check if stockData is an array and take the first element
        //const stockDataObject = Array.isArray(stockData)
        //  ? stockData[0]
        //  : stockData;
        // Continue processing the stockData object
        //setStockOverviewInfo({ data: stockDataObject });
      }
    } catch (error) {
      console.error("Error loading stock data:", error);
      setError("An unexpected error occurred.");
    }
  };

  const handleColumnChange = (columns) => {
    setSelectedColumns(columns);
  };

  const openDialog = () => {
    setIsDialogOpen(true);
  };

  const closeDialog = () => {
    setIsDialogOpen(false);
  };

  const handleCancel = () => {
    closeDialog();
  };

  const handleConfirm = (columns) => {
    setSelectedColumns(columns);
    closeDialog();
  };

  if (!stockOverviewInfo.data) {
    return <div>Loading...</div>;
  }

  const handleCloseErrorDialog = () => {
    setError(null);
  };

  /*
  const deleteUser = async (id) => {
    await axios.delete(`http://localhost:8080/user/${id}`);
    loadUsers();
  };
*/

  const handleDelete = async (overviewId) => {
    try {
      // Make API call to delete the stock
      const deleteResponse = await fetch(
        `http://localhost:8080/deleteStock?userId=${userId}&overviewId=${overviewId}`,
        {
          method: "DELETE",
        }
      );
      console.log("1 delete userId, overviewId: " + userId + ", " + overviewId);
      if (!deleteResponse.ok) {
        console.log(`HTTP error! Status: ${deleteResponse.status}`);
        throw new Error(`HTTP error! Status: ${deleteResponse.status}`);
      }

      // If delete is successful, reload the stock data
      const stockData = await deleteResponse.json();
      console.log("delete stockData: " + stockData);
      console.log("delete stockData.data: " + stockData.data);

      setStockOverviewInfo({ data: stockData });

      //loadStock(userId, symbol);
    } catch (error) {
      console.error("Error deleting stock:", error);
      setError("An unexpected error occurred while deleting the stock.");
    }
  };

  return (
    <div className="container">
      {error ? (
        <ServerErrorDialog
          errorMessage={error}
          onClose={handleCloseErrorDialog}
        />
      ) : (
        <div>
          <div>AlphaVantage: OVERVIEW</div>
          <button onClick={openDialog}>Select Columns</button>

          <Modal
            isOpen={isDialogOpen}
            onRequestClose={closeDialog}
            contentLabel="Select Columns"
          >
            <ColumnSelector
              columns={
                stockOverviewInfo.data
                  ? Object.keys(stockOverviewInfo.data[0] || {})
                  : []
              } // Assuming data is an array
              selectedColumns={selectedColumns}
              onColumnChange={handleColumnChange}
              onCancel={handleCancel}
              onConfirm={handleConfirm}
            />
          </Modal>

          <div className="py-4">
            <table className="table border shadow">
              <thead>
                <tr>
                  {selectedColumns.map((key) => (
                    <th key={key} scope="col">
                      {key}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {Array.isArray(stockOverviewInfo.data) ? (
                  stockOverviewInfo.data.map((stock, index) => (
                    <tr key={index}>
                      {selectedColumns.map((column) => (
                        <td key={column}>{stock[column]}</td>
                      ))}
                      <td>
                        <button
                          onClick={() =>
                            handleDelete(stock[selectedColumns[0]])
                          }
                        >
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr key="singleRow">
                    {selectedColumns.map((column) => (
                      <td key={column}>{stockOverviewInfo.data[column]}</td>
                    ))}
                    <td>
                      {stockOverviewInfo.data[selectedColumns[0]]}
                      <button
                        onClick={() =>
                          handleDelete(
                            stockOverviewInfo.data[selectedColumns[0]]
                          )
                        }
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

StockOverview.propTypes = {
  symbol: PropTypes.string,
  userId: PropTypes.string,
};

export default StockOverview;
