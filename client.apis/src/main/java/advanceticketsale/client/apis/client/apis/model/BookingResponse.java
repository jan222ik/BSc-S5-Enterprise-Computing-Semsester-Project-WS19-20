package advanceticketsale.client.apis.client.apis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * BookingResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-09T21:29:17.187Z[GMT]")
public class BookingResponse {
    @JsonProperty("tranactionId")
    private Long tranactionId = null;

    @JsonProperty("errMsg")
    private String errMsg = null;

    public BookingResponse tranactionId(Long tranactionId) {
        this.tranactionId = tranactionId;
        return this;
    }

    /**
     * Get tranactionId
     *
     * @return tranactionId
     **/
    //@ApiModelProperty(value = "")
    public Long getTranactionId() {
        return tranactionId;
    }

    public void setTranactionId(Long tranactionId) {
        this.tranactionId = tranactionId;
    }

    public BookingResponse errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    /**
     * Get errMsg
     *
     * @return errMsg
     **/
    //@ApiModelProperty(required = true, value = "")
    @NotNull

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookingResponse bookingResponse = (BookingResponse) o;
        return Objects.equals(this.tranactionId, bookingResponse.tranactionId) &&
                Objects.equals(this.errMsg, bookingResponse.errMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tranactionId, errMsg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BookingResponse {\n");

        sb.append("    tranactionId: ").append(toIndentedString(tranactionId)).append("\n");
        sb.append("    errMsg: ").append(toIndentedString(errMsg)).append("\n");
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
