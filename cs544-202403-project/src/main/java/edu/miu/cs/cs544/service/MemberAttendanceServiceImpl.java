package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.repository.RecordRepository;
import edu.miu.cs.cs544.service.contract.MissedSessionPayload;
import edu.miu.cs.cs544.service.contract.RecordResponsePayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberAttendanceServiceImpl implements MemberAttendanceService {
    @Autowired
    SessionService sessionService;
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    MemberRepository memberRepository;



    @Override
    public Map<AccountType, List<RecordResponsePayload>> getMemberAccountTypeAttendance(Long memberId) {
        Optional<Member> memberResponse = memberRepository.findById(memberId);
        if (memberResponse.isEmpty()) {
            throw new EntityNotFoundException("Member not found: " + memberId);
        }
        Map<AccountType, List<RecordResponsePayload>> result = new HashMap<>();
        /*
        for (AccountType accountType: AccountType.values()){
            List<Record> allByMemberIdAndAccountType = recordRepository.findAllByMemberIdAndAccountType(memberId, accountType);
            result.put(accountType, allByMemberIdAndAccountType);
        }
        */
        Member member = memberResponse.get();
        Map<AccountType, List<RecordResponsePayload>> collect = recordRepository.findAllByMemberId(memberId).stream().map(
                record -> {
                    var x = new RecordResponsePayload();
                    x.setMemberId(record.getMember().getId());
                    x.setScannerId(record.getScanner().getId());
                    x.setScanTime(record.getScanTime());
                    x.setId(record.getId());
                    x.setAccountType(record.getScanner().getEvent().getAccountType());
                    return x;
                }
        ).collect(
                Collectors.groupingBy(record -> {
                    return  record.getAccountType();
                })
        );


        return collect;
    }
}
