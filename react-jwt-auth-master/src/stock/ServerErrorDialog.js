import React from "react";

const ServerErrorDialog = ({ errorMessage, onClose }) => {
  return (
    <div className="error-dialog">
      <p>{errorMessage}</p>
      <button onClick={onClose}>OK</button>
    </div>
  );
};

export default ServerErrorDialog;
