import React, { useState } from "react";
import StockNamSearch from "./StockNamSearch";
import StockOverview from "./StockOverview";

function StockOverviewWithSearch(props) {
  const parameterValue = props.parameter;

  const [selectedStock, setSelectedStock] = useState(null);

  const handleSearch = (symbol) => {
    // Handle search logic
    console.log("StockOverviewWithSearch::handleSearch: Search term:", symbol);
    setSelectedStock(symbol);
  };
  return (
    <div className="container">
      <div>selectedStock: {selectedStock}</div>
      <StockNamSearch onSearch={handleSearch} />
      {selectedStock && (
        <StockOverview userId={parameterValue} symbol={selectedStock} />
      )}
    </div>
  );
}

export default StockOverviewWithSearch;
