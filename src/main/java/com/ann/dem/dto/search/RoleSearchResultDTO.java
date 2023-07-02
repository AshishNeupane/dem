package com.ann.dem.dto.search;

import lombok.Data;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(
        name = "retrieverRolePage", // If @Query does not specify name, the method name will be used by default.
        classes = {
                @ConstructorResult(
                        targetClass= RoleSearchResultDTO.class,
                        columns = {
                                @ColumnResult( name = "id", type = Long.class),
                                @ColumnResult( name = "name", type = String.class),
                        }
                )
        }
)

@Data
public class RoleSearchResultDTO extends SearchResultDTO{
    private Long id;
    private String name;

    public RoleSearchResultDTO(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
}


