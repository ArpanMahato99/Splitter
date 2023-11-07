package com.bitspilani.wilp.splitter.dto;

import com.bitspilani.wilp.splitter.enums.TransactionStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionUserDTO {
    private String userId;
    private Float amount;
    private TransactionStatus status;
}