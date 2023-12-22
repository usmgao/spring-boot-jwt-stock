import React, { useEffect, useState } from "react";
import PropTypes from "prop-types"; // Import PropTypes
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
      loadStock(userId, symbol); // Use the provided symbol or default to "abbv"
    }
  }, [userId, symbol]);

  const loadStock = async (userId, symbol) => {
    const functionName = "OVERVIEW";
    const backendUrl = `http://localhost:8080/stock?userId=${userId}&symbol=${symbol}&functionName=${functionName}`;

    try {
      const response = await fetch(backendUrl);
      console.log("StockOverview response from fetch: " + response);

      //const responseText = await response.text();
      //console.log("StockOverview response text: " + responseText);

      if (!response.ok) {
        console.log("HTTP error! Status: ${response.status}");
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const stockData = await response.json();
      console.log("StockOverview stockData: " + stockData);

      //const stockDataStr = JSON.stringify(stockData);
      console.log(
        "StockOverview stringify stockData: " + JSON.stringify(stockData)
      );

      // Use Object.prototype.hasOwnProperty
      if (Object.prototype.hasOwnProperty.call(stockData, "ErrorFromService")) {
        const errorValue = stockData.ErrorFromService;
        setError(errorValue);
      } else {
        // Continue processing the stockData object
        setStockOverviewInfo({ data: stockData });
        console.log(
          "StockOverview::stockOverviewInfo:loadStock: " +
            JSON.stringify(stockOverviewInfo.data)
        );
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
    // Render loading state or return null
    return <div>Loading...</div>;
  }

  const handleCloseErrorDialog = () => {
    setError(null);
  };
  const handleDelete = (symbol) => {
    console.log("delete sysbol: ", symbol);
    // Handle the delete logic here
    //onDeleteStock(symbol);
  };

  return (
    <div className="container">
      {/* Render the main component or the error dialog based on the presence of an error */}
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
              columns={Object.keys(stockOverviewInfo.data)}
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
                      {selectedColumns.map((key) => (
                        <td key={key}>{stock[key]}</td>
                      ))}
                      <td>
                        <button onClick={() => handleDelete(stock.symbol)}>
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr key="singleRow">
                    {selectedColumns.map((key) => (
                      <td key={key}>{stockOverviewInfo.data[key]}</td>
                    ))}
                    <td>
                      <button
                        onClick={() =>
                          handleDelete(stockOverviewInfo.data.symbol)
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

// PropTypes validation
StockOverview.propTypes = {
  symbol: PropTypes.string, // Validate symbol prop as a string
  userId: PropTypes.string,
};

export default StockOverview;
