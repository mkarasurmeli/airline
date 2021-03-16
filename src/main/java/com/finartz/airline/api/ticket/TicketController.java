package com.finartz.airline.api.ticket;

import com.finartz.airline.domain.ticket.Ticket;
import com.finartz.airline.domain.ticket.TicketDTO;
import com.finartz.airline.domain.ticket.TicketMapper;
import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import com.finartz.airline.rest.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
@Api(description = "Bilet ile ilgili kaydetme,güncelleme ve silme işlemleri yapılır.")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    @ApiOperation("Verilen parametrelere göre bilet kayıtlarını döndürür.")
    public ApiResponse<Page<TicketDTO>> getTicketList(Pageable pageable) {

        Page<Ticket> ticketPage = ticketService.findAll(pageable);
        List<TicketDTO> ticketDTOList =
                ticketPage
                        .stream()
                        .map(TicketMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(ticketDTOList, pageable, ticketPage.getTotalElements()));
    }

    @GetMapping("/ticket-number/{ticketNumber}")
    @ApiOperation("Verilen id'ye göre bilet kaydını döndürür.")
    public ApiResponse<TicketDTO> getTicketByTicketNumber(@PathVariable String ticketNumber) {
        Ticket ticket = ticketService.findByTicketNumber(ticketNumber).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        return ApiResponse.of(TicketMapper.INSTANCE.to(ticket));
    }

    @GetMapping("/ticket-cancellation/{ticketNumber}")
    @ApiOperation("Verilen id'ye göre bilet kaydını döndürür.")
    public ApiResponse CancelTicketByTicketNumber(@PathVariable String ticketNumber) {
        ticketService.CancelTicketByTicketNumber(ticketNumber);
        return ApiResponse.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("Verilen id'ye göre bilet kaydını döndürür.")
    public ApiResponse<TicketDTO> getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        return ApiResponse.of(TicketMapper.INSTANCE.to(ticket));
    }

    @PostMapping
    @ApiOperation("Yeni bilet kaydı oluşturur.")
    public ApiResponse<TicketDTO> save(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = TicketMapper.INSTANCE.from(ticketDTO);
        ticket = ticketService.save(ticket);
        return ApiResponse.of(TicketMapper.INSTANCE.to(ticket), ResponseCode.DATA_SAVE_SUCCESS);
    }

    @PutMapping
    @ApiOperation("Mevcut bilet kaydını günceller.")
    public ApiResponse<TicketDTO> update(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = TicketMapper.INSTANCE.from(ticketDTO);
        ticket = ticketService.update(ticket);
        return ApiResponse.of(TicketMapper.INSTANCE.to(ticket), ResponseCode.DATA_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Id bilgisi verilen bilet kaydını siler.")
    public ApiResponse delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ApiResponse.success(ResponseCode.DATA_DELETE_SUCCESS);
    }
}
