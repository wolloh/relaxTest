# Features:
This API provides  endpoint's for the following:
* Get sequences with maximum length : `GET/get_sequence?type=ASCENDING`
* Get sequences with minimum length : `GET/get_sequence?type=DESCENDING`
* Get minimum value : `GET/get_min_value`
* Get median : `GET/get_median`
* Get maximum value : `GET/get_max_value`
* Get arithmetic mean : `GET/get_arithmetic_mean`
* Upload file path: `POST/upload_file`
## Details

`GET/get_sequence?type=ASCENDING`
 
 This endpoint  is called to get ascending sequences with maximum length from file 

### Return

```yaml
  {
    "data": [
        [
            -48190694,
            -47725447,
            -43038241,
            -20190291,
            -17190728,
            -6172572,
            8475960,
            25205909,
            48332507,
            48676185
        ]
    ]
}
```
`GET/get_sequence?type=DESCENDING`

This endpoint is called to get descending sequences with minimum length from file

### Return

```yaml
  {
    "data": [
        [
            47689379,
            42381213,
            30043880,
            12102356,
            -4774057,
            -5157723,
            -11217378,
            -23005285,
            -23016933,
            -39209115,
            -49148762
        ]
    ]
}
```
`GET/get_min_value`

This endpoint is called to get minimum value from file 

### Return

```yaml
 {
    "data": -49999996
}
```
`GET/get_median`

This endpoint is called to get median from file 

### Return

```yaml
{
    "data": 25216.0
}
```
`GET/get_max_value`

This endpoint is called to get maximum value  from file 

### Return

```yaml
{
    "data": 49999978
}
```
`GET/get_arithmetic_mean`

This endpoint is called to get arithmetic mean  value  from file 

### Return

```yaml
{
    "data": 7364.418442641844
}
```

`POST/upload_file`
This endpoint is called to upload file path to api 

### Body 

```yaml
{
    "pathFile":"C:/Users/popov/Downloads/10m.txt"
}
```

### Return

```yaml
{
    "message": "C:/Users/popov/Downloads/10m.txt Successfully upload"
}
```

### Swagger

Swagger available at `http://localhost:{your-port}/swagger-ui/index.html`
