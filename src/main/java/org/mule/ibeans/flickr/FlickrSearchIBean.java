/*
 * $Id: FlickrSearchIBean.java 214 2010-09-08 23:56:43Z ross $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.ibeans.flickr;

import org.mule.tools.cloudconnect.annotations.Operation;
import org.mule.tools.cloudconnect.annotations.Parameter;

import java.awt.image.BufferedImage;
import java.net.URL;

import org.ibeans.annotation.Call;
import org.ibeans.annotation.State;
import org.ibeans.annotation.Template;
import org.ibeans.annotation.param.Body;
import org.ibeans.annotation.param.Optional;
import org.ibeans.annotation.param.ReturnType;
import org.ibeans.annotation.param.UriParam;
import org.ibeans.api.CallException;
import org.ibeans.api.channel.HTTP;
import org.w3c.dom.Node;

/**
 * The iBeans API that provides access to Flickr search functions.  Note that the <code>init()</code> methods on this
 * interface doesn't require the Flickr secret key because search does not require anything other than the API key.
 */
public interface FlickrSearchIBean extends FlickrBase
{

    /**
     * The default format for the Flickr API
     */
    @UriParam("format")
    public static final FORMAT DEFAULT_FORMAT = FORMAT.XML;

    /**
     * The default image format when returning images
     */
    @UriParam("image_type")
    public static final IMAGE_TYPE DEFAULT_IMAGE_TYPE = IMAGE_TYPE.Jpeg;

    /**
     * The default image format when returning images
     */
    @UriParam("image_size")
    public static final IMAGE_SIZE DEFAULT_IMAGE_SIZE = IMAGE_SIZE.Small;

    /**
     * Init the FlickrIBean with your API key
     *
     * @param apikey your Flickr API key. One can be obtained from here: http://www.flickr.com/services/apps/create/apply
     */
    @State
    public void init(@UriParam("api_key") String apikey);

    /**
     * Init the FlickrIBean with your API key and response data format
     *
     * @param apikey your Flickr API key. One can be obtained from here: http://www.flickr.com/services/apps/create/apply
     * @param format The response data format to request from the Flickr API
     */
    @State
    public void init(@UriParam("api_key") String apikey, @UriParam("format") FlickrBase.FORMAT format);

    /**
     * Init the FlickrIBean with your API key and response data format
     *
     * @param apikey     your Flickr API key. One can be obtained from here: http://www.flickr.com/services/apps/create/apply
     * @param format     The response data format to request from the Flickr API
     * @param returnType The Java return type to use when return the response to the caller. he Flickr API supports
     *                   XML and JSON, valid values are String, org.mule.module.json.JsonData, org.w3c.dom.Document.
     */
    @State
    public void init(@UriParam("api_key") String apikey, @UriParam("format") FlickrBase.FORMAT format, @ReturnType() Class<?> returnType);

    /**
     * Return a list of photos with one or more matching tags. Only photos visible to the calling user will be returned. To return
     * private or semi-private photos, the caller must be authenticated with 'read' permissions, and have permission to
     * view the photos. Unauthenticated calls will only return public photos.
     *
     * {@code
     * <flickr:search-tags tags="mulesoft"/>
     * }
     *
     * @param tags    A comma-delimited list of tags. Photos with one or more of the tags listed will be returned.
     * @param tagMode Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.
     * @param <T>     The return type class defined in the {@link FlickrIBean#init(String, org.mule.ibeans.flickr.FlickrBase.FORMAT, Class)} or
     *                {@link FlickrIBean#init(String, org.mule.ibeans.flickr.FlickrBase.FORMAT)} or {@link FlickrIBean#init(String, String, org.mule.ibeans.flickr.FlickrBase.FORMAT, Class)}
     *                methods or uses the default return type {@link FlickrBase#DEFAULT_RETURN_TYPE}. The Flickr API supports XML and JSON, valid values are {@link String},
     *                {@link org.mule.module.json.JsonData}, {@link org.w3c.dom.Document}.
     * @return The result of the search in the format defined by param <T>, if authentication fails if the API key does not validate.
     * @throws CallException if there is an error making the request or the request returns an error
     */
    @Operation
    @Call(uri = "http://www.flickr.com/services/rest?method=flickr.photos.search&api+key={api_key}&tags={tags}&tag_mode={tag_mode}&per_page=10&format={format}&nojsoncallback=1", properties = {HTTP.GET})
    public <T> T searchTags(@UriParam("tags") String tags, @Parameter(optional=true) @Optional @UriParam("tag_mode") String tagMode) throws CallException;

    /**
     * Return a list of photos matching some criteria. Only photos visible to the calling user will be returned. To return
     * private or semi-private photos, the caller must be authenticated with 'read' permissions, and have permission to
     * view the photos. Unauthenticated calls will only return public photos.
     *
     * {@code
     * <flickr:search text="mulesoft" page="1" perPage="20"/>
     * }
     *
     * @param text    A free text search. Photos who's title, description or tags contain the text will be returned.
     * @param perPage Number of photos to return per page. If this argument is omitted, it defaults to 100. The maximum allowed value is 500.
     * @param page    The page of results to return. If this argument is omitted, it defaults to 1.
     * @param <T>     The return type class defined in the {@link FlickrIBean#init(String, org.mule.ibeans.flickr.FlickrBase.FORMAT, Class)} or
     *                {@link FlickrIBean#init(String, org.mule.ibeans.flickr.FlickrBase.FORMAT)} or {@link FlickrIBean#init(String, String, org.mule.ibeans.flickr.FlickrBase.FORMAT, Class)}
     *                methods or uses the default return type {@link FlickrBase#DEFAULT_RETURN_TYPE}. The Flickr API supports XML and JSON, valid values are {@link String},
     *                {@link org.mule.module.json.JsonData}, {@link org.w3c.dom.Document}.
     * @return The result of the search in the format defined by param <T>
     * @throws CallException if there is an error making the request or the request returns an error
     */
    @Operation
    @Call(uri = "http://www.flickr.com/services/rest?method=flickr.photos.search&api+key={api_key}&text={text}&per_page={per_page}&page={page}&format={format}&nojsoncallback=1", properties = {HTTP.GET})
    public <T> T search(@UriParam("text") String text, @Parameter(optional=true, defaultValue="10") @Optional @UriParam("per_page") Integer perPage, @Parameter(optional=true, defaultValue="1") @Optional @UriParam("page") Integer page) throws CallException;

    /**
     * Return a list of photos matching some criteria. Only photos visible to the calling user will be returned. To return
     * private or semi-private photos, the caller must be authenticated with 'read' permissions, and have permission to
     * view the photos. Unauthenticated calls will only return public photos.
     *
     * {@code
     * <flickr:advanced-search userId="34234534" tags="mulesoft"
     *                         lat="-34.56" lon="78.3"/>
     * }
     *
     * @param userId          The NSID of the user who's photo to search. If this parameter isn't passed then everybody's public photos will be searched. A value of "me" will search against the calling user's photos for authenticated calls.
     * @param tags            A comma-delimited list of tags. Photos with one or more of the tags listed will be returned.
     * @param tagsMode        Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.
     * @param text            A free text search. Photos who's title, description or tags contain the text will be returned.
     * @param minUploadDate   Minimum upload date. Photos with an upload date greater than or equal to this value will be returned. The date should be in the form of a unix timestamp.
     * @param maxUploadDate   Maximum upload date. Photos with an upload date less than or equal to this value will be returned. The date should be in the form of a unix timestamp.
     * @param minTakenDate    Minimum taken date. Photos with an taken date greater than or equal to this value will be returned. The date should be in the form of a mysql datetime.
     * @param maxTakenDate    Maximum taken date. Photos with an taken date less than or equal to this value will be returned. The date should be in the form of a mysql datetime.
     * @param license         The license id for photos (for possible values see the flickr.photos.licenses.getInfo method). Multiple licenses may be comma-separated.
     * @param sort            The order in which to sort returned photos. Deafults to date-posted-desc (unless you are doing a radial geo query, in which case the default sorting is by ascending distance from the point specified). The possible values are: date-posted-asc, date-posted-desc, date-taken-asc, date-taken-desc, interestingness-desc, interestingness-asc, and relevance.
     * @param privacyFilter   Return photos only matching a certain privacy level. This only applies when making an authenticated call to view photos you own. Valid values are: 1 public photos. 2 private photos visible to friends. 3 private photos visible to family. 4 private photos visible to friends & family. 5 completely private photos
     * @param bbox            A comma-delimited list of 4 values defining the Bounding Box of the area that will be searched. The 4 values represent the bottom-left corner of the box and the top-right corner, minimum_longitude, minimum_latitude, maximum_longitude, maximum_latitude.
     * @param accuracy        Recorded accuracy level of the location information. Current range is 1-16 : World level is 1. Country is ~3. Region is ~6. City is ~11. Street is ~16. Defaults to maximum value if not specified.
     * @param safeSearch      Safe search setting: 1 for safe. 2 for moderate. 3 for restricted. (Please note: Un-authed calls can only see Safe content.)
     * @param contentType     Content Type setting: 1 for photos only. 2 for screenshots only. 3 for 'other' only. 4 for photos and screenshots. 5 for screenshots and 'other'. 6 for photos and 'other'. 7 for photos, screenshots, and 'other' (all).
     * @param machineTags     Aside from passing in a fully formed machine tag, there is a special syntax for searching on specific properties. Multiple machine tags may be queried by passing a comma-separated list. The number of machine tags you can pass in a single query depends on the tag mode (AND or OR) that you are querying with. "AND" queries are limited to (16) machine tags. "OR" queries are limited to (8).
     * @param machineTagsMode Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.
     * @param groupId         The id of a group who's pool to search. If specified, only matching photos posted to the group's pool will be returned.
     * @param contacts        Search your contacts. Either 'all' or 'ff' for just friends and family. (Experimental)
     * @param woeId           A 32-bit identifier that uniquely represents spatial entities. (not used if bbox argument is present).
     * @param placeId         A Flickr place id. (not used if bbox argument is present).
     * @param media           Filter results by media type. Possible values are all (default), photos or videos
     * @param hasGeo          Any photo that has been geotagged, or if the value is "0" any photo that has not been geotagged.
     * @param geoContext      Geo context is a numeric value representing the photo's geotagginess beyond latitude and longitude. For example, you may wish to search for photos that were taken "indoors" or "outdoors". The current list of context IDs is : 0, not defined. 1, indoors. 2, outdoors.
     * @param lat             A valid latitude, in decimal format, for doing radial geo queries.
     * @param lon             A valid longitude, in decimal format, for doing radial geo queries.
     * @param radius          A valid radius used for geo queries, greater than zero and less than 20 miles (or 32 kilometers), for use with point-based geo queries. The default value is 5 (km).
     * @param radiusUnits     The unit of measure when doing radial geo queries. Valid options are "mi" (miles) and "km" (kilometers). The default is "km".
     * @param isCommons       Limit the scope of the search to only photos that are part of the Flickr Commons project. Default is false.
     * @param extras          A comma-delimited list of extra information to fetch for each returned record. Currently supported fields are: license, date_upload, date_taken, owner_name, icon_server, original_format, last_update, geo, tags, machine_tags, o_dims, views, media, path_alias, url_sq, url_t, url_s, url_m, url_o
     * @param perPage         Number of photos to return per page. If this argument is omitted, it defaults to 100. The maximum allowed value is 500.
     * @param page            The page of results to return. If this argument is omitted, it defaults to 1.
     * @param <T>             The Flickr API supports XML and JSON, valid values are String, org.mule.module.json.JsonData, org.w3c.dom.Document.
     * @return The result of the search in the format defined by param <T>, if authentication fails if the API key does not validate.
     * @throws CallException if there is an error making the request or the request returns an error
     */
    @Operation(name = "advancedSearch")
    @Call(uri = "http://www.flickr.com/services/rest?method=flickr.photos.search&api+key={api_key}&tags={tags}&tags_mode={tags_mode}&text={text}&min_upload_date={min_upload_date}&max_upload_date={max_upload_date}&min_taken_date={min_taken_date}&max_taken_date={max_taken_date}&license={license}&sort={sort}&privacy_filter={privacy_filter}&bbox={bbox}&accuracy={accuracy}&safe_search={safe_search}&content_type={content_type}&machine_tags={machine_tags}&machine_tags_mode={machine_tags_mode}&group_id={group_id}&contacts={contacts}&woe_id={woe_id}&place_id={place_id}&media={media}&has_geo={has_geo}&geo_context={geo_context}&lat={lat}&lon={lon}&radius={radius}&radius_units={radius_units}&is_commons={is_commons}&extras={extras}&per_page=10&format={format}&nojsoncallback={nojsoncallback}", properties = {HTTP.GET})
    public <T> T search(@Parameter(optional = true) @Optional @UriParam("user_id") String userId,
                        @Parameter(optional = true) @Optional @UriParam("tags") String tags,
                        @Parameter(optional = true) @Optional @UriParam("tags_mode") String tagsMode,
                        @Parameter(optional = true) @Optional @UriParam("text") String text,
                        @Parameter(optional = true) @Optional @UriParam("min_upload_date") String minUploadDate,
                        @Parameter(optional = true) @Optional @UriParam("max_upload_date") String maxUploadDate,
                        @Parameter(optional = true) @Optional @UriParam("min_taken_date") String minTakenDate,
                        @Parameter(optional = true) @Optional @UriParam("max_taken_date") String maxTakenDate,
                        @Parameter(optional = true) @Optional @UriParam("license") String license,
                        @Parameter(optional = true) @Optional @UriParam("sort") String sort,
                        @Parameter(optional = true) @Optional @UriParam("privacy_filter") String privacyFilter,
                        @Parameter(optional = true) @Optional @UriParam("bbox") String bbox,
                        @Parameter(optional = true) @Optional @UriParam("accuracy") String accuracy,
                        @Parameter(optional = true) @Optional @UriParam("safe_search") String safeSearch,
                        @Parameter(optional = true) @Optional @UriParam("content_type") String contentType,
                        @Parameter(optional = true) @Optional @UriParam("machine_tags") String machineTags,
                        @Parameter(optional = true) @Optional @UriParam("machine_tags_mode") String machineTagsMode,
                        @Parameter(optional = true) @Optional @UriParam("group_id") String groupId,
                        @Parameter(optional = true) @Optional @UriParam("contacts") String contacts,
                        @Parameter(optional = true) @Optional @UriParam("woe_id") String woeId,
                        @Parameter(optional = true) @Optional @UriParam("place_id") String placeId,
                        @Parameter(optional = true) @Optional @UriParam("media") String media,
                        @Parameter(optional = true) @Optional @UriParam("has_geo") String hasGeo,
                        @Parameter(optional = true) @Optional @UriParam("geo_context") String geoContext,
                        @Parameter(optional = true) @Optional @UriParam("lat") String lat,
                        @Parameter(optional = true) @Optional @UriParam("lon") String lon,
                        @Parameter(optional = true) @Optional @UriParam("radius") String radius,
                        @Parameter(optional = true) @Optional @UriParam("radius_units") String radiusUnits,
                        @Parameter(optional = true) @Optional @UriParam("is_commons") String isCommons,
                        @Parameter(optional = true) @Optional @UriParam("extras") String extras,
                        @Parameter(optional = true) @Optional @UriParam("page") String page,
                        @Parameter(optional = true) @Optional @UriParam("per_page") String perPage,
                        @Parameter(optional = true) @Optional @UriParam("nojsoncallback") String noJsonCallback) throws CallException;

    /**
     * Loads a Photo from Flickr as a java.awt.image.BufferedImage
     *
     * {@code
     * <flickr:get-photo photoUrl="http://www.flickr.com/photos/orangeacid/459207903/"/>
     * }
     *
     * @param photoUrl the Photo URL to download.
     * @return a BufferedImage representation of the photo
     * @throws org.ibeans.api.CallException
     */
    @Operation
    @Call(uri = "{photo_url}", properties = {HTTP.FOLLOW_REDIRECTS})
    public BufferedImage getPhoto(@UriParam("photo_url") String photoUrl) throws CallException;

    /**
     * Will construct a Photo URL from a photo node retuend from a search
     *
     * {@code
     * <flickr:get-photo id="2636" server="2" secret="a123456"/>
     * }
     *
     * @param server Server under which the photo is hosted
     * @param id Id of the photo
     * @param secret Secret
     * @return the new URL
     * @throws org.ibeans.api.CallException if there is a problem parsing the node, (which is very unlikely)
     */
    @Operation
    @Template("http://static.flickr.com/{server}/{id}_{secret}_{image_size}.{image_type}")
    public URL getPhotoURL(@UriParam("server") String server, @UriParam("id") String id, @UriParam("secret") String secret) throws CallException;

    /**
     * Will construct a Photo URL from a photo node retuend from a search
     *
     * @param photoNode the node to construct the URL from
     * @param size      the size of the image that the URL should point to
     * @param type      the type of the image that the URL should point to
     * @return the new URL
     * @throws org.ibeans.api.CallException if there is a problem parsing the node, (which is very unlikely)
     */
    @Template("http://static.flickr.com/#[xpath2:@server]/#[xpath2:@id]_#[xpath2:@secret]_{image_size}.{image_type}")
    public URL getPhotoURL(@Body Node photoNode, @UriParam("image_size") FlickrBase.IMAGE_SIZE size, @UriParam("image_type") FlickrBase.IMAGE_TYPE type) throws CallException;
}