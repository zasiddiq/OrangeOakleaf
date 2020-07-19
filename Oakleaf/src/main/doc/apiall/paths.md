
<a name="paths"></a>
## Resources

<a name="user-controller_resource"></a>
### User Controller
User Controller


<a name="loginusingpost"></a>
#### Check the login information of the user
```
POST /user/login
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**id**  <br>*optional*||integer (int64)|
|**Query**|**password**  <br>*required*|Password|string|
|**Query**|**userType**  <br>*optional*||integer (int32)|
|**Query**|**username**  <br>*required*|Username|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="logoutusingpost"></a>
#### logout
```
POST /user/logout
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="registusingpost"></a>
#### Add a User
```
POST /user/regist
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**id**  <br>*optional*||integer (int64)|
|**Query**|**password**  <br>*required*|Password--Length up to 255|string|
|**Query**|**userType**  <br>*required*|Use numbers 1-6 to indicate the user type|string|
|**Query**|**username**  <br>*required*|Username--Length up to 255|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`



