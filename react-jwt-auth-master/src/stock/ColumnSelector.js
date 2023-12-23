import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";

const ColumnSelector = ({
  columns,
  selectedColumns,
  onColumnChange,
  onCancel,
  onConfirm,
}) => {
  console.log("ColumnSelector: columns", columns);
  console.log("ColumnSelector: selectedColumns", selectedColumns);

  const [tempSelectedColumns, setTempSelectedColumns] =
    useState(selectedColumns);

  useEffect(() => {
    setTempSelectedColumns(selectedColumns);
  }, [selectedColumns]);

  const getObjectKeys = () => {
    // Assuming there is at least one object in the array
    const firstObject = columns[0];
    if (firstObject) {
      return Object.keys(firstObject);
    }
    return [];
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

  const keys = getObjectKeys();

  return (
    <div>
      <h3>Select Display Columns</h3>
      {keys.map((column, index) => (
        <div key={index}>
          <label>
            <input
              type="checkbox"
              checked={tempSelectedColumns.includes(column)}
              onChange={() => handleColumnChange(column)}
            />
            {column}
          </label>
        </div>
      ))}
      <button onClick={handleCancel}>Cancel</button>
      <button onClick={handleConfirm}>Confirm</button>
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

/*
import React, { useState } from "react";
import PropTypes from "prop-types";

const ColumnSelector = ({
  columns,
  selectedColumns,
  onColumnChange,
  onCancel,
  onConfirm,
}) => {
  console.log("ColumnSelector:columns: " + columns);

  const [tempSelectedColumns, setTempSelectedColumns] =
    useState(selectedColumns);

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

  return (
    <div>
      <h3>Select Display Columns</h3>
      {columns.map((column) => (
        <div key={column}>
          <label>
            <input
              type="checkbox"
              checked={tempSelectedColumns.includes(column)}
              onChange={() => handleColumnChange(column)}
            />
            {column}
          </label>
        </div>
      ))}
      <button onClick={handleCancel}>Cancel</button>
      <button onClick={handleConfirm}>Confirm</button>
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
*/
