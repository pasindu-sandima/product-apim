/*
 * WSO2 API Manager - Publisher API
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Publisher**.  # Authentication The Publisher REST API is protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_publisher\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_publisher\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api123\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for publisher REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<servlet_port>/oauth2/token ``` **Sample request** ``` curl https://localhost:9443/oauth2/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:api_view apim:api_create\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:api_create apim:api_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a09044034b5c3c1b01a9) 
 *
 * The version of the OpenAPI document: v3
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.publisher.api.v1.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.wso2.am.integration.clients.publisher.api.v1.dto.ApplicationInfoDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* SubscriptionDTO
*/

public class SubscriptionDTO {
        public static final String SERIALIZED_NAME_SUBSCRIPTION_ID = "subscriptionId";
        @SerializedName(SERIALIZED_NAME_SUBSCRIPTION_ID)
            private String subscriptionId;

        public static final String SERIALIZED_NAME_APPLICATION_INFO = "applicationInfo";
        @SerializedName(SERIALIZED_NAME_APPLICATION_INFO)
            private ApplicationInfoDTO applicationInfo;

        public static final String SERIALIZED_NAME_THROTTLING_POLICY = "throttlingPolicy";
        @SerializedName(SERIALIZED_NAME_THROTTLING_POLICY)
            private String throttlingPolicy;

            /**
* Gets or Sets subscriptionStatus
*/
    @JsonAdapter(SubscriptionStatusEnum.Adapter.class)
public enum SubscriptionStatusEnum {
        BLOCKED("BLOCKED"),
        
        PROD_ONLY_BLOCKED("PROD_ONLY_BLOCKED"),
        
        UNBLOCKED("UNBLOCKED"),
        
        ON_HOLD("ON_HOLD"),
        
        REJECTED("REJECTED"),
        
        TIER_UPDATE_PENDING("TIER_UPDATE_PENDING");

private String value;

SubscriptionStatusEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static SubscriptionStatusEnum fromValue(String value) {
    for (SubscriptionStatusEnum b : SubscriptionStatusEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<SubscriptionStatusEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final SubscriptionStatusEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public SubscriptionStatusEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return SubscriptionStatusEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_SUBSCRIPTION_STATUS = "subscriptionStatus";
        @SerializedName(SERIALIZED_NAME_SUBSCRIPTION_STATUS)
            private SubscriptionStatusEnum subscriptionStatus;


        public SubscriptionDTO subscriptionId(String subscriptionId) {
        
        this.subscriptionId = subscriptionId;
        return this;
        }

    /**
        * Get subscriptionId
    * @return subscriptionId
    **/
      @ApiModelProperty(example = "01234567-0123-0123-0123-012345678901", required = true, value = "")
    
    public String getSubscriptionId() {
        return subscriptionId;
    }


    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }


        public SubscriptionDTO applicationInfo(ApplicationInfoDTO applicationInfo) {
        
        this.applicationInfo = applicationInfo;
        return this;
        }

    /**
        * Get applicationInfo
    * @return applicationInfo
    **/
      @ApiModelProperty(required = true, value = "")
    
    public ApplicationInfoDTO getApplicationInfo() {
        return applicationInfo;
    }


    public void setApplicationInfo(ApplicationInfoDTO applicationInfo) {
        this.applicationInfo = applicationInfo;
    }


        public SubscriptionDTO throttlingPolicy(String throttlingPolicy) {
        
        this.throttlingPolicy = throttlingPolicy;
        return this;
        }

    /**
        * Get throttlingPolicy
    * @return throttlingPolicy
    **/
      @ApiModelProperty(example = "Unlimited", required = true, value = "")
    
    public String getThrottlingPolicy() {
        return throttlingPolicy;
    }


    public void setThrottlingPolicy(String throttlingPolicy) {
        this.throttlingPolicy = throttlingPolicy;
    }


        public SubscriptionDTO subscriptionStatus(SubscriptionStatusEnum subscriptionStatus) {
        
        this.subscriptionStatus = subscriptionStatus;
        return this;
        }

    /**
        * Get subscriptionStatus
    * @return subscriptionStatus
    **/
      @ApiModelProperty(example = "BLOCKED", required = true, value = "")
    
    public SubscriptionStatusEnum getSubscriptionStatus() {
        return subscriptionStatus;
    }


    public void setSubscriptionStatus(SubscriptionStatusEnum subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            SubscriptionDTO subscription = (SubscriptionDTO) o;
            return Objects.equals(this.subscriptionId, subscription.subscriptionId) &&
            Objects.equals(this.applicationInfo, subscription.applicationInfo) &&
            Objects.equals(this.throttlingPolicy, subscription.throttlingPolicy) &&
            Objects.equals(this.subscriptionStatus, subscription.subscriptionStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId, applicationInfo, throttlingPolicy, subscriptionStatus);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class SubscriptionDTO {\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    applicationInfo: ").append(toIndentedString(applicationInfo)).append("\n");
    sb.append("    throttlingPolicy: ").append(toIndentedString(throttlingPolicy)).append("\n");
    sb.append("    subscriptionStatus: ").append(toIndentedString(subscriptionStatus)).append("\n");
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

