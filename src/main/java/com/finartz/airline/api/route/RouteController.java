package com.finartz.airline.api.route;

import com.finartz.airline.domain.route.Route;
import com.finartz.airline.domain.route.RouteDTO;
import com.finartz.airline.domain.route.RouteMapper;
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
@RequestMapping("/route")
@Api(description = "Rota ile ilgili kaydetme,güncelleme ve silme işlemleri yapılır.")
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    @ApiOperation("Verilen parametrelere göre rota kayıtlarını döndürür.")
    public ApiResponse<Page<RouteDTO>> getRouteList(Pageable pageable) {

        Page<Route> routePage = routeService.findAll(pageable);
        List<RouteDTO> routeDTOList =
                routePage
                        .stream()
                        .map(RouteMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(routeDTOList, pageable, routePage.getTotalElements()));
    }

    @GetMapping("/departure/name/{departure}")
    @ApiOperation("Verilen parametrelere göre rota kayıtlarını döndürür.")
    public ApiResponse<Page<RouteDTO>> getRouteListByDepartureName(@PathVariable("departure") String departureName, Pageable pageable) {

        Page<Route> routePage = routeService.findAllByDepartureName(departureName, pageable);
        List<RouteDTO> routeDTOList =
                routePage
                        .stream()
                        .map(RouteMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(routeDTOList, pageable, routePage.getTotalElements()));
    }

    @GetMapping("/destination/name/{destination}")
    @ApiOperation("Verilen parametrelere göre rota kayıtlarını döndürür.")
    public ApiResponse<Page<RouteDTO>> getRouteListByDestinationName(@PathVariable("destination") String destinationName, Pageable pageable) {

        Page<Route> routePage = routeService.findAllByDestinationName(destinationName, pageable);
        List<RouteDTO> routeDTOList =
                routePage
                        .stream()
                        .map(RouteMapper.INSTANCE::to)
                        .collect(Collectors.toList());

        return ApiResponse.of(new PageImpl<>(routeDTOList, pageable, routePage.getTotalElements()));
    }


    @GetMapping("/{id}")
    @ApiOperation("Verilen id'ye göre rota kaydını döndürür.")
    public ApiResponse<RouteDTO> getRoute(@PathVariable Long id) {
        Route route = routeService.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND));
        return ApiResponse.of(RouteMapper.INSTANCE.to(route));
    }


    @PostMapping
    @ApiOperation("Yeni rota kaydı oluşturur.")
    public ApiResponse<RouteDTO> save(@Valid @RequestBody RouteDTO routeDTO) {
        Route route = RouteMapper.INSTANCE.from(routeDTO);
        route = routeService.save(route);
        return ApiResponse.of(RouteMapper.INSTANCE.to(route), ResponseCode.DATA_SAVE_SUCCESS);
    }

    @PutMapping
    @ApiOperation("Mevcut rota kaydını günceller.")
    public ApiResponse<RouteDTO> update(@Valid @RequestBody RouteDTO routeDTO) {
        Route route = RouteMapper.INSTANCE.from(routeDTO);
        route = routeService.update(route);
        return ApiResponse.of(RouteMapper.INSTANCE.to(route), ResponseCode.DATA_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Id bilgisi verilen rota kaydını siler.")
    public ApiResponse delete(@PathVariable Long id) {
        routeService.delete(id);
        return ApiResponse.success(ResponseCode.DATA_DELETE_SUCCESS);
    }
}
