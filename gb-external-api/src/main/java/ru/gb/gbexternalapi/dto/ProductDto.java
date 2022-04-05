package ru.gb.gbexternalapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import ru.gb.gbexternalapi.dto.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title;
    @NotNull
    private BigDecimal cost;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate manufactureDate;
    @NotNull
    private Status status;
    private String manufacturer;
}
