package com.micro.productservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.Objects;

/**
 * InlineResponse200Model
 */

@JsonTypeName("inline_response_200")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-19T02:13:19.227652+01:00[Europe/Madrid]")
public class InlineResponse200Model implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("Product")
  private ProductModel product;

  public InlineResponse200Model product(ProductModel product) {
    this.product = product;
    return this;
  }

  /**
   * Get product
   * @return product
  */
  @Valid 
  @Schema(name = "Product", required = false)
  public ProductModel getProduct() {
    return product;
  }

  public void setProduct(ProductModel product) {
    this.product = product;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200Model inlineResponse200 = (InlineResponse200Model) o;
    return Objects.equals(this.product, inlineResponse200.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200Model {\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

