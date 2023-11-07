package com.bitspilani.wilp.splitter.utils;

import com.bitspilani.wilp.splitter.dto.*;
import com.bitspilani.wilp.splitter.enums.ConnectionStatus;
import com.bitspilani.wilp.splitter.model.Connection;
import com.bitspilani.wilp.splitter.model.Transaction;
import com.bitspilani.wilp.splitter.model.TransactionDetails;
import com.bitspilani.wilp.splitter.model.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GeneralUtils {
    public static User buildUser(UserDTO userDTO) {
         return User.builder()
                 .fullName(userDTO.getFullName())
                 .hashedPass(userDTO.getPass())
                 .email(userDTO.getEmail())
                 .upiId(userDTO.getUpiId())
                 .phoneNumber(userDTO.getPhoneNumber())
                 .build();
    }

    public static UserDTO buildUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId().toHexString())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .upiId(user.getUpiId())
                .build();
    }

    public static Connection buildConnection(ConnectionRequestDTO connectionDTO, ConnectionStatus connectionStatus) {
        return Connection.builder()
                .user1Id(new ObjectId(connectionDTO.getUser1Id()))
                .user2Id(new ObjectId(connectionDTO.getUser2Id()))
                .status(connectionStatus)
                .build();
    }

    public static ConnectionResponseDTO buildConnectionResponseDTO(Connection connection, List<User> userList){
        return ConnectionResponseDTO.builder()
                .connectionId(connection.getConnectionId().toHexString())
                .user1(GeneralUtils.buildConnectionUserDTO(userList.get(0)))
                .user2(GeneralUtils.buildConnectionUserDTO(userList.get(1)))
                .status(connection.getStatus())
                .build();
    }

    public static ConnectionResponseUserDTO buildConnectionUserDTO(User user) {
        return ConnectionResponseUserDTO.builder()
                .userId(user.getUserId().toHexString())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .upiId(user.getUpiId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static Transaction buildTransaction(TransactionRequestDTO transactionDTO) {
        List<TransactionDetails> transactionUsers = new ArrayList<>();
        transactionDTO.getUsers().forEach(transactionUserDTO ->
                transactionUsers.add(GeneralUtils.buildTransactionUser(transactionUserDTO)));
        return Transaction.builder()
                .description(transactionDTO.getDescription())
                .paidBy(new ObjectId(transactionDTO.getPaidBy()))
                .totalAmount(transactionDTO.getTotalAmount())
                .timestamp(transactionDTO.getTimestamp() != null ? transactionDTO.getTimestamp() : LocalDateTime.now())
                .users(transactionUsers)
                .build();
    }

    public static TransactionResponseDTO buildTransactionResponseDTO(
            Transaction transaction, List<TransactionResponseDetailsDTO> detailsList) {

        return TransactionResponseDTO.builder()
                .transactionId(transaction.getTransactionId().toHexString())
                .description(transaction.getDescription())
                .paidBy(transaction.getPaidBy().toHexString())
                .totalAmount(transaction.getTotalAmount())
                .timestamp(transaction.getTimestamp())
                .details(detailsList)
                .build();
    }


    public static TransactionDetails buildTransactionUser(TransactionResponseDetailsDTO transactionUserDTO) {
        return TransactionDetails.builder()
                .userId(new ObjectId(transactionUserDTO.get()))
                .amount(transactionUserDTO.getAmount())
                .status(transactionUserDTO.getStatus())
                .build();
    }

    public static TransactionResponseDetailsDTO buildTransactionUserDTO(TransactionDetails transactionUser) {
        return TransactionResponseDetailsDTO.builder()
                .userId(transactionUser.getUserId().toHexString())
                .amount(transactionUser.getAmount())
                .status(transactionUser.getStatus())
                .build();
    }

    public static TransactionResponseUserDTO buildTransactionResponseUserDTO(User user) {
        return TransactionResponseUserDTO.builder()
                .userId(user.getUserId().toHexString())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .upiId(user.getUpiId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static TransactionResponseDetailsDTO buildTransactionResponseDetailsDTO(
            TransactionDetails transactionDetails, TransactionResponseUserDTO user) {
        return TransactionResponseDetailsDTO.builder()
                .amount(transactionDetails.getAmount())
                .status(transactionDetails.getStatus())
                .user(user)
                .build();
    }
}