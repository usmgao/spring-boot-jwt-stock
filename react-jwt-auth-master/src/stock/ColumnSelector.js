import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import axios from "axios"; // Import Axios for making HTTP requests

import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap styles

const ColumnSelector = ({
  columns,
  selectedColumns,
  onColumnChange,
  onCancel,
  onConfirm,
}) => {
  const [tempSelectedColumns, setTempSelectedColumns] =
    useState(selectedColumns);
  const [selectionName, setSelectionName] = useState(""); // New state for selection name
  const [savedSelections, setSavedSelections] = useState([]); // State to store saved selections

  useEffect(() => {
    setTempSelectedColumns(selectedColumns);
  }, [selectedColumns]);

  useEffect(() => {
    // Fetch saved selections when the component mounts
    fetchSavedSelections();
  }, []);

  const fetchSavedSelections = async () => {
    try {
      // Make a request to your backend to get saved selections
      const response = await axios.get("/api/saved-selections");
      setSavedSelections(response.data);
    } catch (error) {
      console.error("Error fetching saved selections:", error);
    }
  };

  const saveSelection = async () => {
    try {
      // Make a request to your backend to save the selection
      await axios.post("/api/save-selection", {
        name: selectionName,
        columns: tempSelectedColumns,
      });
      // Fetch the updated list of saved selections
      fetchSavedSelections();
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
