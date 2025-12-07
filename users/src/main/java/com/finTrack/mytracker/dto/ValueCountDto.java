package com.finTrack.mytracker.dto;

import com.finTrack.mytracker.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValueCountDto {
    private Category category;
    private Long total;
}
