/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.supersim.v1;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class IpCommandCreator extends Creator<IpCommand> {
    private final String sim;
    private final String payload;
    private final Integer devicePort;
    private IpCommand.PayloadType payloadType;
    private URI callbackUrl;
    private HttpMethod callbackMethod;

    /**
     * Construct a new IpCommandCreator.
     *
     * @param sim The sid or unique_name of the Super SIM to send the IP Command to
     * @param payload The payload to be delivered to the device
     * @param devicePort The device port to which the IP Command will be sent
     */
    public IpCommandCreator(final String sim,
                            final String payload,
                            final Integer devicePort) {
        this.sim = sim;
        this.payload = payload;
        this.devicePort = devicePort;
    }

    /**
     * Indicates how the payload is encoded. Either `text` or `binary`. Defaults to
     * `text`..
     *
     * @param payloadType Indicates how the payload is encoded
     * @return this
     */
    public IpCommandCreator setPayloadType(final IpCommand.PayloadType payloadType) {
        this.payloadType = payloadType;
        return this;
    }

    /**
     * The URL we should call using the `callback_method` after we have sent the IP
     * Command..
     *
     * @param callbackUrl The URL we should call after we have sent the IP Command
     * @return this
     */
    public IpCommandCreator setCallbackUrl(final URI callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    /**
     * The URL we should call using the `callback_method` after we have sent the IP
     * Command..
     *
     * @param callbackUrl The URL we should call after we have sent the IP Command
     * @return this
     */
    public IpCommandCreator setCallbackUrl(final String callbackUrl) {
        return setCallbackUrl(Promoter.uriFromString(callbackUrl));
    }

    /**
     * The HTTP method we should use to call `callback_url`. Can be `GET` or `POST`,
     * and the default is `POST`..
     *
     * @param callbackMethod The HTTP method we should use to call callback_url
     * @return this
     */
    public IpCommandCreator setCallbackMethod(final HttpMethod callbackMethod) {
        this.callbackMethod = callbackMethod;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created IpCommand
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public IpCommand create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SUPERSIM.toString(),
            "/v1/IpCommands"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("IpCommand creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return IpCommand.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (sim != null) {
            request.addPostParam("Sim", sim);
        }

        if (payload != null) {
            request.addPostParam("Payload", payload);
        }

        if (devicePort != null) {
            request.addPostParam("DevicePort", devicePort.toString());
        }

        if (payloadType != null) {
            request.addPostParam("PayloadType", payloadType.toString());
        }

        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
        }

        if (callbackMethod != null) {
            request.addPostParam("CallbackMethod", callbackMethod.toString());
        }
    }
}