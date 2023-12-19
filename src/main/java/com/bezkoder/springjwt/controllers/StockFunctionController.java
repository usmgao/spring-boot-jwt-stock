package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.HelpUtil;
import com.bezkoder.springjwt.exception.StockFunctionNameNotFoundException;
import com.bezkoder.springjwt.models.StockOverview;
import com.bezkoder.springjwt.repository.StockOverviewRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bezkoder.springjwt.models.User;
//import com.ming.boot.exception.StockFunctionNameNotFoundException;
//import com.ming.boot.model.StockOverview;
//import com.ming.boot.model.User;
//import com.ming.boot.repository.StockOverviewRepository;
//import com.ming.boot.repository.UserRepository;
//import com.ming.boot.HelpUtil;
//import com.ming.boot.controller.StockApiConstents;

/* Created by Ming  */
@RestController
//@CrossOrigin("http://localhost:3000")
public class StockFunctionController {

	@Autowired
	private StockOverviewRepository stockOverviewRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/overview")
	StockOverview newOverview(@RequestBody StockOverview overview) {
		HelpUtil.ErrorServerLog("newOverview: " + overview);
		return stockOverviewRepository.save(overview);
	}

	@PutMapping("/overview/{id}")
	StockOverview updateUser(@RequestBody StockOverview newOverriew, @PathVariable Long id) {
		return stockOverviewRepository.findById(id).map(overview -> {

			overview.setSymbol(newOverriew.getSymbol());
			overview.setName(newOverriew.getName());
			overview.setAssetType(newOverriew.getAssetType());
			overview.setDescription(newOverriew.getDescription());
			overview.setcIK(newOverriew.getcIK());
			overview.setExchange(newOverriew.getExchange());
			overview.setCurrency(newOverriew.getCurrency());
			overview.setCountry(newOverriew.getCountry());
			overview.setSector(newOverriew.getSector());
			overview.setIndustry(newOverriew.getIndustry());
			overview.setAddress(newOverriew.getAddress());
			overview.setFiscalYearEnd(newOverriew.getFiscalYearEnd());
			overview.setLatestQuarter(newOverriew.getLatestQuarter());
			overview.setMarketCapitalization(newOverriew.getMarketCapitalization());
			overview.seteBITDA(newOverriew.geteBITDA());
			overview.setpERatio(newOverriew.getpERatio());
			overview.setpEGRatio(newOverriew.getpEGRatio());
			overview.setBookValue(newOverriew.getBookValue());
			overview.setDividendPerShare(newOverriew.getDividendPerShare());
			overview.setDividendYield(newOverriew.getDividendYield());
			overview.setePS(newOverriew.getePS());
			overview.setRevenuePerShareTTM(newOverriew.getRevenuePerShareTTM());
			overview.setProfitMargin(newOverriew.getProfitMargin());
			overview.setOperatingMarginTTM(newOverriew.getOperatingMarginTTM());
			overview.setReturnOnAssetsTTM(newOverriew.getReturnOnAssetsTTM());
			overview.setReturnOnEquityTTM(newOverriew.getReturnOnEquityTTM());
			overview.setRevenueTTM(newOverriew.getRevenueTTM());
			overview.setGrossProfitTTM(newOverriew.getGrossProfitTTM());
			overview.setDilutedEPSTTM(newOverriew.getDilutedEPSTTM());
			overview.setQuarterlyRevenueGrowthYOY(newOverriew.getQuarterlyRevenueGrowthYOY());
			overview.setQuarterlyEarningsGrowthYOY(newOverriew.getQuarterlyEarningsGrowthYOY());
			overview.setAnalystTargetPrice(newOverriew.getAnalystTargetPrice());
			overview.setTrailingPE(newOverriew.getTrailingPE());
			overview.setForwardPE(newOverriew.getForwardPE());
			overview.setPriceToSalesRatioTTM(newOverriew.getPriceToSalesRatioTTM());
			overview.setPriceToBookRatio(newOverriew.getPriceToBookRatio());
			overview.seteVToRevenue(newOverriew.geteVToRevenue());
			overview.seteVToEBITDA(newOverriew.geteVToEBITDA());
			overview.setBeta(newOverriew.getBeta());
			overview.set_52WeekHigh(newOverriew.get_52WeekHigh());
			overview.set_52WeekLow(newOverriew.get_52WeekLow());
			overview.set_50DayMovingAverage(newOverriew.get_50DayMovingAverage());
			overview.set_200DayMovingAverage(newOverriew.get_200DayMovingAverage());
			overview.setSharesOutstanding(newOverriew.getSharesOutstanding());
			overview.setDividendDate(newOverriew.getDividendDate());
			overview.setExDividendDate(newOverriew.getExDividendDate());

			return stockOverviewRepository.save(overview);
		}).orElseThrow(() -> new StockFunctionNameNotFoundException(id));
	}

	@DeleteMapping("/overview/{id}")
	String deleteUser(@PathVariable Long id) {
		if (!stockOverviewRepository.existsById(id)) {
			throw new StockFunctionNameNotFoundException(id);
		}
		stockOverviewRepository.deleteById(id);
		return "StockOverview with id " + id + " has been deleted success.";
	}

	private boolean isUserAssociatedWithStock(StockOverview stockOverview, String userName) {
		return stockOverview.getUsers().stream().anyMatch(user -> user.getUsername().equals(userName));
	}

	@GetMapping("/stock")
	@Transactional
	public String getStockFunctionResponseValue(@RequestParam String symbol, @RequestParam String functionName,
			@RequestParam String userId) {
		HelpUtil.ErrorServerLog("========= from /stock path ======== " + LocalDateTime.now());
		HelpUtil.ErrorServerLog("getStockFunctionResponseValue input: " + symbol + ", " + functionName + ", " + userId);
		String result = HelpUtil.ErrorFromServerToClint("initial default value: null");

		if ((symbol == null) || symbol.equals("null") || (symbol.length() == 0)) {
			HelpUtil.ErrorServerLog("getStockFunctionResponseValue: input symbol is null");
			return HelpUtil.ErrorFromServerToClint("input symbol is null, stop further process");
		} 

		if (functionName.equals("OVERVIEW")) {
			// Step 1: Check if the stock exists in the database
			HelpUtil.ErrorServerLog("Step 1: Check if the stock exists in the database");
			Optional<StockOverview> optionalStockOverview = stockOverviewRepository.findBySymbol(symbol);
			if (optionalStockOverview.isPresent()) {
				StockOverview stockOverview = optionalStockOverview.get();

				// Step 2: Check if the user is in the list of users associated with that stock
				HelpUtil.ErrorServerLog("Step 2: Check if the user is in the list of users associated with that stock");
				if (!isUserAssociatedWithStock(stockOverview, userId)) {
					// If the user is not associated, add the user to the stock
					HelpUtil.ErrorServerLog("If the user is not associated, add the user to the stock");
					User user = userRepository.findByUsername(userId)
							.orElseThrow(() -> new IllegalArgumentException("User not found"));
					stockOverview.getUsers().add(user);

				    // Update the StockOverview (and cascade the update to associated users)
					HelpUtil.ErrorServerLog("Update the StockOverview (and cascade the update to associated users)");
	                stockOverviewRepository.save(stockOverview);

	                // Also update the User entity to maintain the bidirectional relationship
	                HelpUtil.ErrorServerLog("Also update the User entity to maintain the bidirectional relationship");
	                user.getStockOverviews().add(stockOverview);
	                userRepository.save(user);
				} else {
					// If the user is already associated, you might handle it accordingly
					HelpUtil.ErrorFromServerToClint("If the user is already associated, get list StockOverview later");
				}
			} else {
				// Step 3: If the stock does not exist, you might handle it accordingly
				HelpUtil.ErrorFromServerToClint("Step 3: If the stock does not exist, you might handle it accordingly");
				try {
					HelpUtil.ErrorServerLog("getStockFunctionResponseValue go online to get data: " + symbol);
					result = OVERVIEW(symbol);
					if ((result == null) || result.equals("null") || (result.length() == 0)) {
						HelpUtil.ErrorServerLog("api call result is null");
						return HelpUtil.ErrorFromServerToClint("api call result is null");
					}

					// make sure it is not a 25/day over run information
					HelpUtil.ErrorServerLog("getStockFunctionResponseValue get online result: " + result);
					try {
						ObjectMapper objectMapper = new ObjectMapper();
						JsonNode jsonNode = objectMapper.readTree(result);

						// Get the first entry in the JSON object
						if (jsonNode.isObject() && jsonNode.fields().hasNext()) {
							// Get the first entry (key-value pair)
							java.util.Map.Entry<String, JsonNode> entry = jsonNode.fields().next();
							String firstKey = entry.getKey();
							String firstValue = entry.getValue().asText();

							// Print the first key and value
							HelpUtil.ErrorServerLog("First Key: " + firstKey);
							HelpUtil.ErrorServerLog("First Value: " + firstValue);
							if (firstKey.equals("Information"))
								return HelpUtil.ErrorFromServerToClint("api call result: " + firstValue);
						} else {
							return HelpUtil.ErrorFromServerToClint("JSON object is empty.");
						}
					} catch (Exception e) {
						return HelpUtil.ErrorFromServerToClint("exception message: " + e.getMessage() + ", cause: " + e.getCause());
					}

					saveApiResult(userId, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return HelpUtil.ErrorFromServerToClint("exception message: " + e.getMessage() + ", cause: " + e.getCause());
				}
			}
		} else
			result = HelpUtil.ErrorFromServerToClint("functionName: "+ functionName +" unknow");

		// HelpUtil.ErrorServerLog("getStockFunctionResponseValue result: "+result);

		// load all symbol by id
		HelpUtil.ErrorFromServerToClint("load all symbol by user id");
		User user = userRepository.findByUsername(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		List<StockOverview> userStocks = user.getStockOverviews();
		HelpUtil.ErrorServerLog("findByUserId userStocks size: " + userStocks.size());
		for(int i = 0; i < userStocks.size(); i++) {
			HelpUtil.ErrorServerLog("findByUserId stock: "+userStocks.get(i).getSymbol());
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			result = objectMapper.writeValueAsString(userStocks);
			 HelpUtil.ErrorServerLog("getStockFunctionResponseValue json converted from db: " +
			 result);
		} catch (JsonProcessingException e) {
			result = HelpUtil.ErrorFromServerToClint("bad db list StockOverview convert result exception message, cause: "+e.getMessage()+" "+e.getCause());
		}

		return result;
	}

	private String OVERVIEW(String symbol) throws IOException, InterruptedException {
		HelpUtil.ErrorServerLog("api OVERVIEW: " + symbol);
		StringBuilder sb = new StringBuilder();
		String requestStr = sb.append(StockApiConstents.BASE_URL_AlphaVantage).append("OVERVIEW").append("&apikey=")
				.append(StockApiConstents.MY_KEY_AlphaVantage).append("&symbol=").append(symbol).toString();
		HelpUtil.ErrorServerLog("AlphaVantage requestStr: " + requestStr);
		return getResponse("AlphaVantage", requestStr);
	}

	private String quote(String symbol) throws IOException, InterruptedException {
		// https://api.iex.cloud/v1/data/core/quote/msft?token=pk_c9555cf295d2426db6a8bc49d766165e
		StringBuilder sb = new StringBuilder();
		String requestStr = sb.append(StockApiConstents.BASE_URL_IEXCloud).append("quote").append("/").append(symbol)
				.append("?token=").append(StockApiConstents.MY_KEY_IEXCloud).toString();
		// https://api.iex.cloud/v1/data/core/quote?token=pk_c9555cf295d2426db6a8bc49d766165e
		// GET

		HelpUtil.ErrorServerLog("IEXCloud requestStr: " + requestStr);
		return getResponse("IEXCloud", requestStr);
	}

	private String getResponse(String apiCo, String requestStr) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestStr)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		HelpUtil.ErrorServerLog(apiCo + ": " + response.body());
		return response.body().toString();
	}

	private boolean saveApiResult(String userName, String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();

		// Convert JSON string to Java object
		try {
			StockOverview overview = objectMapper.readValue(jsonString, StockOverview.class);
			if (overview == null)
				return false;

			// HelpUtil.ErrorServerLog("add 3 extra data to entity");
			overview.setUpdateBy(userName);
			overview.setUserId(userName);
			overview.setUpdateDate(new Date());

			User user = userRepository.findByUsername(userName)
					.orElseThrow(() -> new IllegalArgumentException("User not found"));

			overview.getUsers().add(user);

			// Save the StockOverview (and cascade the save to associated users)
			stockOverviewRepository.save(overview);

			// HelpUtil.ErrorServerLog("before call newOverview");
			// StockOverview newOne = newOverview(overview);
			// HelpUtil.ErrorServerLog("saveApiResult symbol: " + newOne.getSymbol());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
}