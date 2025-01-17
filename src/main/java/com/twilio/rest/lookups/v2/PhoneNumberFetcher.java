/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.lookups.v2;

import com.twilio.base.Fetcher;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class PhoneNumberFetcher extends Fetcher<PhoneNumber> {
    private final String pathPhoneNumber;
    private String fields;
    private String countryCode;

    /**
     * Construct a new PhoneNumberFetcher.
     *
     * @param pathPhoneNumber Phone number to lookup
     */
    public PhoneNumberFetcher(final String pathPhoneNumber) {
        this.pathPhoneNumber = pathPhoneNumber;
    }

    /**
     * A comma-separated list of fields to return. Possible values are caller_name,
     * sim_swap, call_forwarding, live_activity, enhanced_line_type or
     * line_type_intelligence..
     *
     * @param fields Fields to return
     * @return this
     */
    public PhoneNumberFetcher setFields(final String fields) {
        this.fields = fields;
        return this;
    }

    /**
     * The <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">country
     * code</a> used if the phone number provided is in national format..
     *
     * @param countryCode Country code for national phone number lookups
     * @return this
     */
    public PhoneNumberFetcher setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched PhoneNumber
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public PhoneNumber fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.LOOKUPS.toString(),
            "/v2/PhoneNumbers/" + this.pathPhoneNumber + ""
        );

        addQueryParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("PhoneNumber fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return PhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (fields != null) {
            request.addQueryParam("Fields", fields);
        }

        if (countryCode != null) {
            request.addQueryParam("CountryCode", countryCode);
        }
    }
}