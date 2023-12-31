package com.bitspilani.wilp.splitter.controller;

import com.bitspilani.wilp.splitter.dto.*;
import com.bitspilani.wilp.splitter.service.ConnectionService;
import com.bitspilani.wilp.splitter.service.TransactionService;
import com.bitspilani.wilp.splitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ConnectionService connectionService;
    private final TransactionService transactionService;

    @PostMapping("signup")
    public ResponseEntity<UserDTO> signup(@RequestBody final UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody final LoginDTO loginDTO) {
        UserDTO user = userService.loginUser(loginDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<UserDTO> searchUser(@RequestParam(name = "q") String query) {
        UserDTO user = userService.searchUser(query);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("{userId}/connections")
    public ResponseEntity<List<ConnectionResponseDTO>> getConnectionList (@PathVariable("userId") final String userId) {
        List<ConnectionResponseDTO> connectionList = connectionService.getConnectionList(userId);
        return new ResponseEntity<>(connectionList, HttpStatus.OK);
    }

    @GetMapping("{userId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions (@PathVariable final String userId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByUserId(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
