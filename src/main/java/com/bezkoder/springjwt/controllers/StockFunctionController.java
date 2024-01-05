package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.bezkoder.springjwt.exception.UserNotFoundException;
import com.bezkoder.springjwt.models.ColumnSelection;
import com.bezkoder.springjwt.models.StockOverview;
import com.bezkoder.springjwt.models.StockOverviewColumn;
import com.bezkoder.springjwt.repository.ColumnSelectionRepository;
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
@CrossOrigin("http://localhost:8081")
public class StockFunctionController {

	@Autowired
	private StockOverviewRepository stockOverviewRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ColumnSelectionRepository columnSelectionRepository;

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
//		if (!stockOverviewRepository.existsById(id)) {
//			throw new StockFunctionNameNotFoundException(id);
//		}
//		stockOverviewRepository.deleteById(id);
		// remove user from stock overview

		return "StockOverview with id " + id + " has been deleted success.";
	}

	@DeleteMapping("/deleteStock")
	public ResponseEntity<String> deleteStock(@RequestParam String userId, @RequestParam String overviewId) {
		HelpUtil.ErrorServerLog("deleteStock ================== userId, overviewId: " + userId + ", " + overviewId);
		String result = "Stock deleted not successful";
		// Perform the logic to delete the stock from the user's stock overview list
		// remove user from stock overview
		StockOverview so = stockOverviewRepository.findById(Long.parseLong(overviewId)).orElseThrow();
		HelpUtil.ErrorServerLog("so: " + so.toSimpleString());

		// get user list from so
		List<User> userFromOverviewOld = so.getUsers();

//		User currentUser = null;
//		if (userFromOverviewOld != null) {
//			for (User u : userFromOverviewOld) {
//				HelpUtil.ErrorServerLog("u   in   so: " + u);
//				if (u.getUsername().equals(userId)) {
//					currentUser = u;
//					HelpUtil.ErrorServerLog("currentUser: " + currentUser);
//				}
//			}
//		}

		// remove user from stock overview so list
		userFromOverviewOld.removeIf(e -> e.getUsername().equals(userId));

//		List<User> userFromOverview = so.getUsers();

		// update so in DB
		StockOverview updatedSo = stockOverviewRepository.save(so);
		HelpUtil.ErrorServerLog("updatedSo after remove currentUser: " + updatedSo.toSimpleString());

//		for (User u : userFromOverview) {
//			HelpUtil.ErrorServerLog("u in new so: " + u);
//		}
//		if (currentUser == null) {
//			return ResponseEntity.ok(result);
//		}
		User user = userRepository.findByUsername(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));

		// get stock overview list from user
		List<StockOverview> stockOverviewFromCurrentUserOld = user.getStockOverviews();
		HelpUtil.ErrorServerLog(" stockOverviewFromCurrentUserOld: " + stockOverviewFromCurrentUserOld);

		// remove stock overview from user list
		stockOverviewFromCurrentUserOld.removeIf(e -> e.getId().toString().equals(overviewId));

		// update user in DB
		User updatedUser = userRepository.save(user);
		HelpUtil.ErrorServerLog("updatedUser: " + updatedUser);

//		List<StockOverview> stockOverviewFromCurrentUser = user.getStockOverviews();
//		stockOverviewRepository.save(stockOverview);
//		HelpUtil.ErrorServerLog("new stockOverviewFromCurrentUser: " + stockOverviewFromCurrentUser);

		// Return a success message or handle errors appropriately
		// get StockOverview list without deleted one from user id
//		User user = userRepository.findByUsername(userId)
//				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		List<StockOverview> userStocks = user.getStockOverviews();
		HelpUtil.ErrorServerLog("userStocks size: " + userStocks.size());
		for (int i = 0; i < userStocks.size(); i++) {
			HelpUtil.ErrorServerLog("stock: " + userStocks.get(i).getSymbol());
		}

//		Authentication authenticationOld = SecurityContextHolder.getContext().getAuthentication();
//		HelpUtil.ErrorServerLog("authenticationOld: " + authenticationOld);
//        // Get the principal (authenticated user) from the authentication object
//        if (authenticationOld != null && authenticationOld.getPrincipal() instanceof User) {
//            User loginUser = (User) authenticationOld.getPrincipal();
//            HelpUtil.ErrorServerLog("loginUser: " + loginUser);
//            List<StockOverview> loginUserStocks = loginUser.getStockOverviews();
//    		HelpUtil.ErrorServerLog("loginUserStocks size: " + loginUserStocks.size());
//    		for (int i = 0; i < loginUserStocks.size(); i++) {
//    			HelpUtil.ErrorServerLog("loginUserStocks: " + userStocks.get(i).getSymbol());
//    		}
//        }
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
//		    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		    HelpUtil.ErrorServerLog("userDetails: " + userDetails);
//		    String userName = userDetails.getUsername();
//		    User userFromUserDetails = userRepository.findByUsername(userName)
//					.orElseThrow(() -> new IllegalArgumentException("User not found"));
//		    HelpUtil.ErrorServerLog("userFromUserDetails: " + userFromUserDetails);
//		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			result = objectMapper.writeValueAsString(userStocks);
			// HelpUtil.ErrorServerLog("getStockFunctionResponseValue json converted from
			// db: " + result);
		} catch (JsonProcessingException e) {
			result = HelpUtil
					.ErrorFromServerToClint("bad db list StockOverview convert result exception message, cause: "
							+ e.getMessage() + " " + e.getCause());
		}

		HelpUtil.ErrorServerLog("delete result: " + result);
		return ResponseEntity.ok(result);
	}

	private boolean isUserAssociatedWithStock(StockOverview stockOverview, String userName) {
		return stockOverview.getUsers().stream().anyMatch(user -> user.getUsername().equals(userName));
	}

	private boolean isUserAssociatedWithColumnSelection(ColumnSelection columnSelection, String userName) {
		return columnSelection.getUsers().stream().anyMatch(user -> user.getUsername().equals(userName));
	}

	@GetMapping("/stock")
	@Transactional
	public String getStockFunctionResponseValue(@RequestParam String symbol, @RequestParam String functionName,
			@RequestParam String userId,
			@RequestParam(name = "selectionName", defaultValue = "default_name") String selectionName) {

		HelpUtil.ErrorServerLog("========= from /stock ======== " + LocalDateTime.now());
		HelpUtil.ErrorServerLog("getStockFunctionResponseValue input symbol: " + symbol + ", functionName: "
				+ functionName + ", userId: " + userId + ", selectionName: " + selectionName);

//		String result = HelpUtil.ErrorFromServerToClint("initial default value: null");
//		HelpUtil.ErrorServerLog("========= from /stock path ======== " + LocalDateTime.now());
//		HelpUtil.ErrorServerLog("getStockFunctionResponseValue input symbol: " + symbol + ", functionName: "
//				+ functionName + ", userId: " + userId);
		String result = HelpUtil.ErrorFromServerToClint("initial default value: null");

		if ((symbol == null) || symbol.equals("null") || (symbol.length() == 0)) {
			HelpUtil.ErrorServerLog("getStockFunctionResponseValue: input symbol is null");
			return HelpUtil.ErrorFromServerToClint("input symbol is null, stop further process");
		}

		if (functionName.equals("OVERVIEW")) {
			Optional<StockOverview> optionalStockOverview = stockOverviewRepository.findBySymbol(symbol);
			HelpUtil.ErrorServerLog(
					"Step 1: Check if the stock exists in the database: " + optionalStockOverview.isPresent());
			if (optionalStockOverview.isPresent()) {
				StockOverview stockOverview = optionalStockOverview.get();
				boolean isUserAssociatedWithStock = isUserAssociatedWithStock(stockOverview, userId);
				// Step 2: Check if the user is in the list of users associated with that stock
				HelpUtil.ErrorServerLog("Step 2: Check if the user is in the list of users associated with that stock: "
						+ isUserAssociatedWithStock);
				if (!isUserAssociatedWithStock) {
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
				}
			} else {
				// Step 3: If the stock does not exist, you might handle it accordingly
				HelpUtil.ErrorFromServerToClint("Step 3: If the stock does not exist, you might handle it accordingly");
				try {
					// HelpUtil.ErrorServerLog("getStockFunctionResponseValue go online to get data:
					// " + symbol);
					result = OVERVIEW(symbol);
					if ((result == null) || result.equals("null") || (result.length() == 0)) {
						HelpUtil.ErrorServerLog("api call result is null");
						return HelpUtil.ErrorFromServerToClint("api call result is null");
					}

					try {
						ObjectMapper objectMapper = new ObjectMapper();
						JsonNode jsonNode = objectMapper.readTree(result);

						// make sure it is not a 25/day over run information
						// Get the first entry in the JSON object
						if (jsonNode.isObject() && jsonNode.fields().hasNext()) {
							// Get the first entry (key-value pair)
							java.util.Map.Entry<String, JsonNode> entry = jsonNode.fields().next();
							String firstKey = entry.getKey();
							String firstValue = entry.getValue().asText();

							// Print the first key and value
							HelpUtil.ErrorServerLog("First Key: " + firstKey);
							HelpUtil.ErrorServerLog("First Value: " + firstValue);
							if (firstKey.equals("Information")) {
								HelpUtil.ErrorServerLog("getStockFunctionResponseValue get online result: " + result);
								return HelpUtil.ErrorFromServerToClint("api call result: " + result);
							}
						} else {
							return HelpUtil.ErrorFromServerToClint("JSON object is empty.");
						}
					} catch (Exception e) {
						HelpUtil.ErrorServerLog("exception message: " + e.getMessage() + ", cause: " + e.getCause());
						return HelpUtil.ErrorFromServerToClint(
								"exception message: " + e.getMessage() + ", cause: " + e.getCause());
					}

					// HelpUtil.ErrorServerLog("before saveApiResult userId, result: " + userId +",
					// result: "+result);
					saveApiResultToDB(userId, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return HelpUtil.ErrorFromServerToClint(
							"exception message: " + e.getMessage() + ", cause: " + e.getCause());
				}
			}
		} else if (functionName.equals("selectionName")) {
//			HelpUtil.ErrorServerLog("functionName=selectionName; input selection: " + symbol + "; functionName: "
//					+ functionName + "; userId: " + userId);
			HelpUtil.ErrorServerLog("========= from /selection ======== " + LocalDateTime.now());
			HelpUtil.ErrorServerLog("getStockFunctionResponseValue input selection: " + symbol + ", functionName: "
					+ functionName + ", userId: " + userId + ", selectionName: " + selectionName);
			// result = HelpUtil.ErrorFromServerToClint("initial default value: null");

			// return HelpUtil.ErrorFromServerToClint("input selections testing");
			result = selectionProcess(symbol, functionName, userId, selectionName);
		} else
			result = HelpUtil.ErrorFromServerToClint("functionName: " + functionName + " unknow");

		HelpUtil.ErrorServerLog("*********** getStockFunctionResponseValue result: "+result);

		// load all symbol by id
		HelpUtil.ErrorServerLog("load all symbol by user id");
		User user = userRepository.findByUsername(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		List<StockOverview> userStocks = user.getStockOverviews();
		HelpUtil.ErrorServerLog("userStocks size: " + userStocks.size());
		for (int i = 0; i < userStocks.size(); i++) {
			HelpUtil.ErrorServerLog("stock: " + userStocks.get(i).getSymbol());
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			result = objectMapper.writeValueAsString(userStocks);
			//HelpUtil.ErrorServerLog("*********** objectMapper result: "+result);
			// HelpUtil.ErrorServerLog("getStockFunctionResponseValue json converted from
			// db: " + result);
		} catch (JsonProcessingException e) {
			result = HelpUtil
					.ErrorFromServerToClint("bad db list StockOverview convert result exception message, cause: "
							+ e.getMessage() + " " + e.getCause());
		}

		return result;
	}

	private String selectionProcess(String selection, String functionName, String userId, String selectionName) {
		HelpUtil.ErrorServerLog("-------------------- selectionProcess ----------------------------");
		String result = HelpUtil.ErrorFromServerToClint("initial default value: null");
		User user = userRepository.findByUsername(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		if ((selection == null) || selection.equals("null") || (selection.length() == 0)) {
			HelpUtil.ErrorServerLog("getStockFunctionResponseValue: input symbol is null");
			return HelpUtil.ErrorFromServerToClint("input symbol is null, stop further process");
		}

		if (functionName.equals("selectionName")) {
			HelpUtil.ErrorServerLog("getStockFunctionResponseValue input symbol: " + selection + ", functionName: "
					+ functionName + ", userId: " + userId + ", selectionName: " + selectionName);
			Optional<ColumnSelection> optionalColumnSelection = columnSelectionRepository
					.findByColumnsSelectionName(selectionName);
			HelpUtil.ErrorServerLog("findByColumnsSelectionName: " + optionalColumnSelection.isPresent());
			if (optionalColumnSelection.isPresent()) {
				ColumnSelection columnSelection = optionalColumnSelection.get();
				boolean isUserAssociatedWithColumnSelection = isUserAssociatedWithColumnSelection(columnSelection,
						userId);
				// Step 2: Check if the user is in the list of users associated with that stock
				HelpUtil.ErrorServerLog(
						"Step 2: Check if the user is in the list of users associated with that selection: "
								+ isUserAssociatedWithColumnSelection);
				if (!isUserAssociatedWithColumnSelection) {
					// If the user is not associated, add the user to the stock
					HelpUtil.ErrorServerLog("If the user is not associated, add the user to the selection");
					columnSelection.getUsers().add(user);

					// Update the StockOverview (and cascade the update to associated users)
					HelpUtil.ErrorServerLog("Update the ColumnSelection (and cascade the update to associated users)");
					columnSelectionRepository.save(columnSelection);

					// Also update the User entity to maintain the bidirectional relationship
					HelpUtil.ErrorServerLog("Also update the User entity to maintain the bidirectional relationship");
					user.getColumnSelections().add(columnSelection);
					userRepository.save(user);
				}
			} else {
				try {
					// Step 3: If the stock does not exist, you might handle it accordingly
					HelpUtil.ErrorServerLog("Step 3: If the selection does not exist, you might handle it accordingly");
					// id,Symbol,DividendPerShare,DividendYield,AnalystTargetPrice,DividendDate,ExDividendDate,PERatio,MarketCapitalization,
					String[] splitString = selection.split(",");
					HelpUtil.ErrorServerLog("splitString: " + splitString);
					// List<String> columnList = Arrays.asList(splitString);
					List<StockOverviewColumn> columns = new ArrayList<>();
					for (String s : splitString) {
						HelpUtil.ErrorServerLog("s: " + s);
						if (s.length() == 0)
							continue;

						StockOverviewColumn c = new StockOverviewColumn();
						c.setColumnName(s);
						columns.add(c);
					}
					ColumnSelection cs = new ColumnSelection();
					cs.setColumns(columns);
					cs.setColumnsSelectionName(selectionName);
					HelpUtil.ErrorServerLog("user: " + user);
					Set<User> users = new HashSet<>();
					users.add(user);
					cs.setUsers(users);
					HelpUtil.ErrorServerLog("=== before savedCS cs: " + cs);
					ColumnSelection savedCS = columnSelectionRepository.save(cs);
					HelpUtil.ErrorServerLog("-------------- savedCS: " + savedCS);
					
					user.getColumnSelections().add(savedCS);					
					userRepository.save(user);					
				} catch (Exception e) {
					result = "????????????????????????????????????? save selection exception message: " + e.getMessage() + ", cause: " + e.getCause();
					HelpUtil.ErrorServerLog(result);
					HelpUtil.ErrorFromServerToClint(result);
				}
			}
		} else
			result = HelpUtil.ErrorFromServerToClint("functionName: " + functionName + " unknow");

		return result;
	}

	@GetMapping("/selection")
	@Transactional
	public String getStockSelectionFunctionResponseValue(@RequestParam String selection,
			@RequestParam String functionName, @RequestParam String userId,
			@RequestParam(name = "selectionName", defaultValue = "default_name") String selectionName) {
		HelpUtil.ErrorServerLog("========= from /selection ======== " + LocalDateTime.now());
		HelpUtil.ErrorServerLog("getStockFunctionResponseValue input selection: " + selection + ", functionName: "
				+ functionName + ", userId: " + userId + ", selectionName: " + selectionName);
		String result = selectionProcess(selection, functionName, userId, selectionName);
		
		HelpUtil.ErrorServerLog("*********** getStockFunctionResponseValue result: "+result);

		// load all symbol by id
		HelpUtil.ErrorServerLog("load all symbol by user id");
		User user = userRepository.findByUsername(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		List<StockOverview> userStocks = user.getStockOverviews();
		HelpUtil.ErrorServerLog("userStocks size: " + userStocks.size());
		for (int i = 0; i < userStocks.size(); i++) {
			HelpUtil.ErrorServerLog("stock: " + userStocks.get(i).getSymbol());
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			result = objectMapper.writeValueAsString(userStocks);
			// HelpUtil.ErrorServerLog("getStockFunctionResponseValue json converted from
			// db: " + result);
		} catch (JsonProcessingException e) {
			result = HelpUtil
					.ErrorFromServerToClint("bad db list StockOverview convert result exception message, cause: "
							+ e.getMessage() + " " + e.getCause());
		}

//		HelpUtil.ErrorServerLog("getStockFunctionResponseValue:selectionProcess result");
//
//		// load all symbol by id
//		HelpUtil.ErrorFromServerToClint("load all selections by user id");
//		HelpUtil.ErrorFromServerToClint("user: " + user);
//		Set<ColumnSelection> userSelections = user.getColumnSelections();
//		HelpUtil.ErrorServerLog("userStocks size: " + userSelections.size());
//		for (ColumnSelection userSelecions : userSelections) {
//			HelpUtil.ErrorServerLog("userSelecions: " + userSelecions);
//		}
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			result = objectMapper.writeValueAsString(userSelections);
//			// HelpUtil.ErrorServerLog("getStockFunctionResponseValue json converted from
//			// db: " + result);
//		} catch (JsonProcessingException e) {
//			result = HelpUtil
//					.ErrorFromServerToClint("bad db list StockOverview convert result exception message, cause: "
//							+ e.getMessage() + " " + e.getCause());
//		}

		return result;
	}
	
	private boolean saveApiResultToDB(String userName, String jsonString) {
		HelpUtil.ErrorServerLog("in saveApiResult userName: " + userName);

		ObjectMapper objectMapper = new ObjectMapper();

		// Convert JSON string to Java object
		try {
			StockOverview overview = objectMapper.readValue(jsonString, StockOverview.class);
			if (overview == null) {
				HelpUtil.ErrorServerLog("overview is null");
				return false;
			}
			HelpUtil.ErrorServerLog("before add 6 extra data to overview symbol: " + overview.getSymbol() + ", users: "
					+ overview.getUsers());
			overview.setCreatedBy(userName);
			overview.setUpdateBy(userName);
			overview.setUserId(userName);
			overview.setCreatedAt(new Date());
			overview.setUpdateAt(new Date());
			overview.setUpdateDate(new Date());

			// get user
			User user = userRepository.findByUsername(userName)
					.orElseThrow(() -> new IllegalArgumentException("User not found"));
			HelpUtil.ErrorServerLog("saveApiResult:user before add to overview: " + user.toSimpleString());

			// add user to overview
			overview.getUsers().add(user);
			// HelpUtil.ErrorServerLog("add overview with User overview: "+overview);

			// Save the StockOverview (and cascade the save to associated users)
			StockOverview updatedOverview = stockOverviewRepository.save(overview);
			HelpUtil.ErrorServerLog("updatedOverview with new User symbol: " + updatedOverview.getSymbol() + ", users: "
					+ updatedOverview.getUsers());

			// add overviews to user
			user.getStockOverviews().add(updatedOverview);
			User updatedUser = userRepository.save(user);
			HelpUtil.ErrorServerLog("updatedUser with updatedOverview: " + updatedUser.toSimpleString());
		} catch (JsonMappingException e) {
			HelpUtil.ErrorServerLog(
					"in saveApiResult JsonMappingException cause: " + e.getCause() + ", message: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (JsonProcessingException e) {
			HelpUtil.ErrorServerLog(
					"in saveApiResult JsonProcessingException cause: " + e.getCause() + ", message: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private String OVERVIEW(String symbol) throws IOException, InterruptedException {
		// HelpUtil.ErrorServerLog("api OVERVIEW: " + symbol);
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

	private String getResponse(String apiCall, String requestStr) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestStr)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		HelpUtil.ErrorServerLog(apiCall + ": " + response.body());
		return response.body().toString();
	}
}