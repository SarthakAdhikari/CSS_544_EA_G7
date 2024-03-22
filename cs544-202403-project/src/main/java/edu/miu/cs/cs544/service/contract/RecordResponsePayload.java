package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Scanner;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RecordResponsePayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime scanTime;
    private Long memberId;
    private Long scannerId;
    private AccountType accountType;
}
