package com.micro.productservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProductModel
 */
@JsonTypeName("Product")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-19T02:13:19.227652+01:00[Europe/Madrid]")
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@Builder // Permite la construcción de objetos con un patrón Builder
public class ProductModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  @NotNull
  @Schema(name = "name", required = true)
  private String name;

  @JsonProperty("description")
  @NotNull
  @Schema(name = "description", required = true)
  private String description;

  @JsonProperty("price")
  @NotNull
  @Schema(name = "price", required = true)
  private BigDecimal price;

  @JsonProperty("brand")
  @Schema(name = "brand", required = false)
  private String brand;
}
