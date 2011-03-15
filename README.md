Cloud Connector for Flickr
==========================

A Flickr Cloud Connector that allows users to search and download photos.

Installation
------------

The connector can either be installed for all applications running within the Mule instance or can be setup to be used
for a single application.

*All Applications*

Download the connector from the link above and place the resulting jar file in
/lib/user directory of the Mule installation folder.

*Single Application*

To make the connector available only to single application then place it in the
lib directory of the application otherwise if using Maven to compile and deploy
your application the following can be done:

Add the connector's maven repo to your pom.xml:

    <repositories>
        <repository>
            <id>muleforge-releases</id>
            <name>MuleForge Snapshot Repository</name>
            <url>https://repository.muleforge.org/release/</url>
            <layout>default</layout>
        </repsitory>
    </repositories>

Add the connector as a dependency to your project. This can be done by adding
the following under the <dependencies> element in the pom.xml file of the
application:

    <dependency>
        <groupId>org.mule.modules</groupId>
        <artifactId>mule-module-flickr</artifactId>
        <version>1.2-SNAPSHOT</version>
    </dependency>

Configuration
-------------

You can configure the connector as follows:

    <flickr:config/>

Here is detailed list of all the configuration attributes:

| attribute | description | optional | default value |
|:-----------|:-----------|:---------|:--------------|
|name|Give a name to this configuration so it can be later referenced by config-ref.|yes||
|apiKey|API Key|no|
|secretKey|Secret Key|no|
|format|Format of the Flickr response|no|















Search Tags
-----------

Return a list of photos with one or more matching tags. Only photos visible to the calling user will be returned. To return
private or semi-private photos, the caller must be authenticated with 'read' permissions, and have permission to
view the photos. Unauthenticated calls will only return public photos.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|tags|    A comma-delimited list of tags. Photos with one or more of the tags listed will be returned.|no||
|tagMode| Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.|yes||

Search
------

Return a list of photos matching some criteria. Only photos visible to the calling user will be returned. To return
private or semi-private photos, the caller must be authenticated with 'read' permissions, and have permission to
view the photos. Unauthenticated calls will only return public photos.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|text|    A free text search. Photos who's title, description or tags contain the text will be returned.|no||
|perPage| Number of photos to return per page. If this argument is omitted, it defaults to 100. The maximum allowed value is 500.|yes|10|
|page|    The page of results to return. If this argument is omitted, it defaults to 1.|yes|1|

Advanced Search
---------------

Return a list of photos matching some criteria. Only photos visible to the calling user will be returned. To return
private or semi-private photos, the caller must be authenticated with 'read' permissions, and have permission to
view the photos. Unauthenticated calls will only return public photos.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|userId|          The NSID of the user who's photo to search. If this parameter isn't passed then everybody's public photos will be searched. A value of "me" will search against the calling user's photos for authenticated calls.|yes||
|tags|            A comma-delimited list of tags. Photos with one or more of the tags listed will be returned.|yes||
|tagsMode|        Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.|yes||
|text|            A free text search. Photos who's title, description or tags contain the text will be returned.|yes||
|minUploadDate|   Minimum upload date. Photos with an upload date greater than or equal to this value will be returned. The date should be in the form of a unix timestamp.|yes||
|maxUploadDate|   Maximum upload date. Photos with an upload date less than or equal to this value will be returned. The date should be in the form of a unix timestamp.|yes||
|minTakenDate|    Minimum taken date. Photos with an taken date greater than or equal to this value will be returned. The date should be in the form of a mysql datetime.|yes||
|maxTakenDate|    Maximum taken date. Photos with an taken date less than or equal to this value will be returned. The date should be in the form of a mysql datetime.|yes||
|license|         The license id for photos (for possible values see the flickr.photos.licenses.getInfo method). Multiple licenses may be comma-separated.|yes||
|sort|            The order in which to sort returned photos. Deafults to date-posted-desc (unless you are doing a radial geo query, in which case the default sorting is by ascending distance from the point specified). The possible values are: date-posted-asc, date-posted-desc, date-taken-asc, date-taken-desc, interestingness-desc, interestingness-asc, and relevance.|yes||
|privacyFilter|   Return photos only matching a certain privacy level. This only applies when making an authenticated call to view photos you own. Valid values are:
                       <p/>
                       1 public photos
                       2 private photos visible to friends
                       3 private photos visible to family
                       4 private photos visible to friends & family
                       5 completely private photos
                       <p/>|yes||
|bbox|            A comma-delimited list of 4 values defining the Bounding Box of the area that will be searched.
                       <p/>
                       The 4 values represent the bottom-left corner of the box and the top-right corner, minimum_longitude, minimum_latitude, maximum_longitude, maximum_latitude.
                       <p/>
                       Longitude has a range of -180 to 180 , latitude of -90 to 90. Defaults to -180, -90, 180, 90 if not specified.
                       <p/>
                       Unlike standard photo queries, geo (or bounding box) queries will only return 250 results per page.
                       <p/>
                       Geo queries require some sort of limiting agent in order to prevent the database from crying. This is basically like the check against "parameterless searches" for queries without a geo component.
                       <p/>
                       A tag, for instance, is considered a limiting agent as are user defined min_date_taken and min_date_upload parameters � If no limiting factor is passed we return only photos added in the last 12 hours (though we may extend the limit in the future).|yes||
|accuracy|        (Optional)
                       Recorded accuracy level of the location information. Current range is 1-16 :
                       <p/>
                       World level is 1
                       Country is ~3
                       Region is ~6
                       City is ~11
                       Street is ~16
                       <p/>
                       Defaults to maximum value if not specified.|yes||
|safeSearch|      (Optional)
                       Safe search setting:
                       <p/>
                       1 for safe.
                       2 for moderate.
                       3 for restricted.
                       <p/>
                       (Please note: Un-authed calls can only see Safe content.)|yes||
|contentType|     Content Type setting:
                       <p/>
                       1 for photos only.
                       2 for screenshots only.
                       3 for 'other' only.
                       4 for photos and screenshots.
                       5 for screenshots and 'other'.
                       6 for photos and 'other'.
                       7 for photos, screenshots, and 'other' (all).
                       <p/>|yes||
|machineTags|     Aside from passing in a fully formed machine tag, there is a special syntax for searching on specific properties :
                       <p/>
                       Find photos using the 'dc' namespace : "machine_tags" => "dc:"
                       Find photos with a title in the 'dc' namespace : "machine_tags" => "dc:title="
                       Find photos titled "mr. camera" in the 'dc' namespace : "machine_tags" => "dc:title=\"mr. camera\"
                       Find photos whose value is "mr. camera" : "machine_tags" => "*:*=\"mr. camera\""
                       Find photos that have a title, in any namespace : "machine_tags" => "*:title="
                       Find photos that have a title, in any namespace, whose value is "mr. camera" : "machine_tags" => "*:title=\"mr. camera\""
                       Find photos, in the 'dc' namespace whose value is "mr. camera" : "machine_tags" => "dc:*=\"mr. camera\""
                       <p/>
                       Multiple machine tags may be queried by passing a comma-separated list. The number of machine tags you can pass in a single query depends on the tag mode (AND or OR) that you are querying with. "AND" queries are limited to (16) machine tags. "OR" queries are limited to (8).|yes||
|machineTagsMode| (Required) - Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.|yes||
|groupId|         The id of a group who's pool to search. If specified, only matching photos posted to the group's pool will be returned.|yes||
|contacts|        Search your contacts. Either 'all' or 'ff' for just friends and family. (Experimental)|yes||
|woeId|           A 32-bit identifier that uniquely represents spatial entities. (not used if bbox argument is present).
                       <p/>
                       Geo queries require some sort of limiting agent in order to prevent the database from crying. This is basically like the check against "parameterless searches" for queries without a geo component.
                       <p/>
                       A tag, for instance, is considered a limiting agent as are user defined min_date_taken and min_date_upload parameters &emdash; If no limiting factor is passed we return only photos added in the last 12 hours (though we may extend the limit in the future).|yes||
|placeId|         A Flickr place id. (not used if bbox argument is present).
                       <p/>
                       Geo queries require some sort of limiting agent in order to prevent the database from crying. This is basically like the check against "parameterless searches" for queries without a geo component.
                       <p/>
                       A tag, for instance, is considered a limiting agent as are user defined min_date_taken and min_date_upload parameters &emdash; If no limiting factor is passed we return only photos added in the last 12 hours (though we may extend the limit in the future).|yes||
|media|           Filter results by media type. Possible values are all (default), photos or videos|yes||
|hasGeo|          (Optional)
                       Any photo that has been geotagged, or if the value is "0" any photo that has not been geotagged.
                       <p/>
                       Geo queries require some sort of limiting agent in order to prevent the database from crying. This is basically like the check against "parameterless searches" for queries without a geo component.
                       <p/>
                       A tag, for instance, is considered a limiting agent as are user defined min_date_taken and min_date_upload parameters &emdash; If no limiting factor is passed we return only photos added in the last 12 hours (though we may extend the limit in the future).|yes||
|geoContext|      Geo context is a numeric value representing the photo's geotagginess beyond latitude and longitude. For example, you may wish to search for photos that were taken "indoors" or "outdoors".
                       <p/>
                       The current list of context IDs is :
                       <p/>
                       0, not defined.
                       1, indoors.
                       2, outdoors.
                       <p/>
                       <p/>
                       Geo queries require some sort of limiting agent in order to prevent the database from crying. This is basically like the check against "parameterless searches" for queries without a geo component.
                       <p/>
                       A tag, for instance, is considered a limiting agent as are user defined min_date_taken and min_date_upload parameters &emdash; If no limiting factor is passed we return only photos added in the last 12 hours (though we may extend the limit in the future).|yes||
|lat|             A valid latitude, in decimal format, for doing radial geo queries.|yes||
|lon|             A valid longitude, in decimal format, for doing radial geo queries.|yes||
|radius|          A valid radius used for geo queries, greater than zero and less than 20 miles (or 32 kilometers), for use with point-based geo queries. The default value is 5 (km).|yes||
|radiusUnits|     The unit of measure when doing radial geo queries. Valid options are "mi" (miles) and "km" (kilometers). The default is "km".|yes||
|isCommons|       Limit the scope of the search to only photos that are part of the Flickr Commons project. Default is false.|yes||
|extras|          A comma-delimited list of extra information to fetch for each returned record. Currently supported fields are: license, date_upload, date_taken, owner_name, icon_server, original_format, last_update, geo, tags, machine_tags, o_dims, views, media, path_alias, url_sq, url_t, url_s, url_m, url_o|yes||
|page|            The page of results to return. If this argument is omitted, it defaults to 1.|yes||
|perPage|         Number of photos to return per page. If this argument is omitted, it defaults to 100. The maximum allowed value is 500.|yes||
|noJsonCallback||yes||

Get Photo
---------

Loads a Photo from Flickr as a {@link java.awt.image.BufferedImage}

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|photoUrl| the Photo URL to download.  Typically this method is used in conjunction with {@link #getPhotoURL(org.w3c.dom.Node, FlickrSearchIBean.IMAGE_SIZE, FlickrSearchIBean.IMAGE_TYPE)} or {@link #getPhotoURL(org.w3c.dom.Node)}|no||

Get Photo U R L
---------------

Will construct a Photo URL from a photo node retuend from a search

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|server||no||
|id||no||
|secret||no||


