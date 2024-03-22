package edu.miu.cs.cs544.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.repository.RecordRepository;
import edu.miu.cs.cs544.service.MemberAttendanceServiceImpl;
import edu.miu.cs.cs544.service.SessionService;
import edu.miu.cs.cs544.service.contract.RecordResponsePayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MemberAttendanceServiceImplTest {

    @Mock
    private SessionService sessionService;

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberAttendanceServiceImpl memberAttendanceService;

    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        Member mockMember = new Member(); // Assume Member class has appropriate setters to set values.
        mockMember.setId(memberId);

        Event eventA = new Event("Event A", "Description A", LocalDateTime.now(), LocalDateTime.now().plusHours(2), null, null, AccountType.DINING);
        Event eventB = new Event("Event B", "Description B", LocalDateTime.now(), LocalDateTime.now().plusHours(3), null, null, AccountType.ATTENDANCE);

        Scanner scannerA = new Scanner();
        scannerA.setEvent(eventA);

        Scanner scannerB = new Scanner();
        scannerB.setEvent(eventB);

        Record record1 = new Record(mockMember, scannerA, new Session());
        Record record2 = new Record(mockMember, scannerB, new Session());

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mockMember));
        when(recordRepository.findAllByMemberId(memberId)).thenReturn(Arrays.asList(record1, record2));
    }

    @Test
    void testGetMemberAccountTypeAttendance() {
        Map<AccountType, List<RecordResponsePayload>> result = memberAttendanceService.getMemberAccountTypeAttendance(memberId);
        assertEquals(2, result.size(), "Should have records for 2 account types.");

        List<RecordResponsePayload> diningRecords = result.get(AccountType.DINING);
        assertEquals(1, diningRecords.size(), "Should have 1 dining record.");

        RecordResponsePayload diningRecord = diningRecords.get(0);
        assertEquals(memberId, diningRecord.getMemberId(), "Member ID should match for DINING record.");
        assertEquals(AccountType.DINING, diningRecord.getAccountType(), "AccountType should be DINING.");

        List<RecordResponsePayload> attendanceRecords = result.get(AccountType.ATTENDANCE);
        assertEquals(1, attendanceRecords.size(), "Should have 1 attendance record.");
        RecordResponsePayload attendanceRecord = attendanceRecords.get(0);

        assertEquals(memberId, attendanceRecord.getMemberId(), "Member ID should match for ATTENDANCE record.");
        assertEquals(AccountType.ATTENDANCE, attendanceRecord.getAccountType(), "AccountType should be ATTENDANCE.");
    }

}
