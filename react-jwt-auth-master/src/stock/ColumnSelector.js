import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import axios from "axios";

import "bootstrap/dist/css/bootstrap.min.css";

const ColumnSelector = ({
  userId,
  columns,
  selectedColumns,
  onColumnChange,
  onCancel,
  onConfirm,
}) => {
  const [tempSelectedColumns, setTempSelectedColumns] =
    useState(selectedColumns);
  const [selectionName, setSelectionName] = useState("");
  const [savedSelections, setSavedSelections] = useState([]);

  console.log("ColumnSelector::userId: " + userId);

  const loadSelection = async (userId) => {
    const symbol = "loadSelection";
    const functionName = "loadSelection";
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
        // setError(errorValue);
      } else {
        // setStockOverviewInfo({ data: stockData });
      }
    } catch (error) {
      console.error("Error loading stock data:", error);
      // setError("An unexpected error occurred.");
    }
  };

  useEffect(() => {
    console.log("======= loadSelection ======");
    loadSelection(userId);

    // Fetch saved selections when the component mounts
    console.log("======= fetchSavedSelections ======");
    const fetchSavedSelections = async () => {
      try {
        // Make a request to your backend to get saved selections
        const response = await axios.get(`/loadselections?userId=${userId}`);
        setSavedSelections(response.data);
      } catch (error) {
        console.error("Error fetching saved selections:", error);
      }
    };

    fetchSavedSelections(); // Call the function directly
  }, [userId]); // Include userId in the dependency array

  const saveSelection = async () => {
    try {
      // Check if the selection name is already saved
      const isNameAlreadySaved = savedSelections.some(
        (selection) => selection.name === selectionName
      );

      if (isNameAlreadySaved) {
        console.error("Selection name is already saved.");
        return;
      }

      // Make a request to your backend to save the selection
      console.log("selectionName: " + selectionName);
      console.log("tempSelectedColumns: " + tempSelectedColumns);
      console.log("ColumnSelector::saveSelection:userId: " + userId);

      const functionName = "saveSelection";
      const stockBackendUrl = `http://localhost:8080/stock?userId=${userId}&symbol=${tempSelectedColumns}&functionName=${functionName}&selectionName=${selectionName}`;

      try {
        console.log("stockBackendUrl: " + stockBackendUrl);
        const response = await fetch(stockBackendUrl);

        if (!response.ok) {
          console.log(`HTTP error! Status: ${response.status}`);
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
      } catch (error) {
        console.error("Error saving selectionName: ", error);
      }
    } catch (error) {
      console.error("Error saving selection:", error);
    }
  };

  const handleColumnChange = (column) => {
    const updatedColumns = tempSelectedColumns.includes(column)
      ? tempSelectedColumns.filter((selected) => selected !== column)
      : [...tempSelectedColumns, column];
    setTempSelectedColumns(updatedColumns);
  };

  const handleCancel = () => {
    setTempSelectedColumns(selectedColumns);
    onCancel();
  };

  const handleConfirm = () => {
    onConfirm(tempSelectedColumns);
  };

  const handleSelectionNameChange = (e) => {
    setSelectionName(e.target.value);
  };

  return (
    <div className="container mt-4">
      <h3>Select Display Columns</h3>
      <div className="mb-3">
        <label htmlFor="selectionName" className="form-label">
          Selection Name:
        </label>
        <input
          type="text"
          id="selectionName"
          className="form-control"
          value={selectionName}
          onChange={handleSelectionNameChange}
        />
      </div>
      {columns.map((column) => (
        <div key={column} className="form-check">
          <input
            type="checkbox"
            className="form-check-input"
            checked={tempSelectedColumns.includes(column)}
            onChange={() => handleColumnChange(column)}
          />
          <label className="form-check-label">{column}</label>
        </div>
      ))}
      <div className="mt-3">
        <button className="btn btn-secondary me-2" onClick={handleCancel}>
          Cancel
        </button>
        <button className="btn btn-primary me-2" onClick={handleConfirm}>
          Confirm
        </button>
        <button className="btn btn-success" onClick={saveSelection}>
          Save Selection
        </button>
      </div>
      <div className="mt-4">
        <h5>Saved Selections:</h5>
        <ul>
          {savedSelections.map((selection) => (
            <li key={selection._id}>{selection.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
};

ColumnSelector.propTypes = {
  columns: PropTypes.array.isRequired,
  selectedColumns: PropTypes.array.isRequired,
  onColumnChange: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired,
  onConfirm: PropTypes.func.isRequired,
};

export default ColumnSelector;
