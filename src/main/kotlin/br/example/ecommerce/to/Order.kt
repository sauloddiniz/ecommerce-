package to

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class Order(
    @JsonProperty("userId")
    val userId: String = "",
    @JsonProperty("orderId")
    val orderId: String = "",
    @JsonProperty("description")
    val description: String = "",
    @JsonProperty("value")
    val value: Double
);