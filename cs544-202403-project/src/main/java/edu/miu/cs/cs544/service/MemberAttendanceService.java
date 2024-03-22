package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.service.contract.MissedSessionPayload;
import edu.miu.cs.cs544.service.contract.RecordResponsePayload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberAttendanceService {
    Map<AccountType, List<RecordResponsePayload>> getMemberAccountTypeAttendance(Long memberId);
}
