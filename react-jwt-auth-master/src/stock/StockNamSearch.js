import React, { useState, useEffect } from "react";
import axios from "axios";
import PropTypes from "prop-types";

const StockNamSearch = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  useEffect(() => {
    const fetchSearchResults = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/stocks/symbol_search?symbol=${searchTerm}`
        );
        const filteredResults = response.data.filter((stock) =>
          matchStock(stock, searchTerm)
        );
        //console.log("filteredResults: " + filteredResults);
        setSearchResults(filteredResults);
      } catch (error) {
        console.error("Error searching stocks:", error);
      }
    };

    // Perform search only if the searchTerm is not empty
    if (searchTerm.length >= 1) {
      fetchSearchResults();
    } else {
      // Clear results when the input is empty
      setSearchResults([]);
    }
  }, [searchTerm]);

  const handleInputChange = (e) => {
    const term = e.target.value.trim();
    setSearchTerm(term);
  };

  const handleSelectStock = (selectedStock) => {
    setSearchTerm(selectedStock.symbol);
    setSearchResults([]); // Clear results after selecting a stock
    onSearch(selectedStock.symbol);
  };

  // Function to check if a stock symbol starts with the user-entered term
  const matchStock = (stock, term) => {
    const lowerCaseTerm = term.toLowerCase();
    return stock.symbol.toLowerCase().startsWith(lowerCaseTerm);
  };

  const handleAddStock = (symbol) => {
    // You can implement the logic to delete the stock with the given symbol
    console.log(
      "StockNamSearch::handleAddStock: Adding stock with symbol:",
      symbol
    );
  };

  return (
    <div>
      <h3>Search by Symbol</h3>
      <input type="text" value={searchTerm} onChange={handleInputChange} />

      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th className="left-align" scope="col">
                Symbol
              </th>
              <th className="left-align" scope="col">
                Company Name
              </th>
              <th className="left-align" scope="col">
                Industry
              </th>
              <th className="left-align" scope="col">
                Market Cap
              </th>
            </tr>
          </thead>
          <tbody>
            {searchResults.map((stock) => (
              <tr key={stock.symbol} onClick={() => handleSelectStock(stock)}>
                <td className="left-align">{stock.symbol}</td>
                <td className="left-align">{stock.companyName}</td>
                <td className="left-align">{stock.industry}</td>
                <td className="left-align">
                  ${stock.marketCap.toLocaleString()}
                </td>
                <td className="left-align">
                  <button onClick={() => handleAddStock(stock.symbol)}>
                    Add
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

StockNamSearch.propTypes = {
  onSearch: PropTypes.func.isRequired,
};

export default StockNamSearch;
