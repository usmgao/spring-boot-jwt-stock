import React, { useState } from "react";
import PropTypes from "prop-types";

// ... (previous imports)
// ... (previous imports)

const ColumnSelector = ({
  columns,
  selectedColumns,
  //onColumnChange,
  onCancel,
  onConfirm,
}) => {
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
