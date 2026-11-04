KVD APPLICATION
===

---

List all entries

```shell
 curl.exe -X GET http://localhost:8080/kv  
```

--- 

Insert entry

```shell
curl.exe -X POST http://localhost:8080/kv/stage -H "Content-Type: text/plain" -d "PRODUCTION" 
```

---

Get entry

```shell
curl.exe -X GET http://localhost:8080/kv/version 
```

---

Update entry

```shell
curl.exe -X PATCH http://localhost:8080/kv/version -H "Content-Type: text/plain" -d "1.1.1"
```


Delete entry

```shell
curl.exe -X DELETE http://localhost:8080/kv/name 
```

Upsert entry

```shell
curl.exe -X PUT http://localhost:8080/kv/version -H "Content-Type: text/plain" -d "1.1.1"
```




