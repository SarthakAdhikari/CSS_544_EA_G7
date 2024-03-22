package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.service.MemberAttendanceService;
import edu.miu.cs.cs544.service.MemberAttendanceServiceImpl;
import edu.miu.cs.cs544.service.MemberEventAttendanceService;
import edu.miu.cs.cs544.service.contract.MissedSessionPayload;
import edu.miu.cs.cs544.service.contract.RecordResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberAttendanceController {
    @Autowired
    MemberAttendanceService memberAttendanceService;

    @GetMapping("/members/{memberId}/attendance")
    public ResponseEntity<?> getMemberAttendance(@PathVariable Long memberId) {
        Map<AccountType, List<RecordResponsePayload>> scanRecords = memberAttendanceService.getMemberAccountTypeAttendance(memberId);
        return ResponseEntity.ok(scanRecords);
    }
}

