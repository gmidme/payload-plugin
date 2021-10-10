# payload plugin for elasticsearch

## When i need use this plugin?
* if the field you want to query has many different tokens like term, and you want to redefine the  score by combine each matched term's predefined different score of each document,
then you can use payload plugin.
* Give a example: image keyword search
each image has different keywords, each keyword within the same image maybe has different weight, that makes sense.
* so at this case, two image both has keywords: dog and car, 
  
  but first one is more about dog, second one is more about car, so you can set different weight in each image about dog and car,

  when user query keyword dog, it would  surely recommend first image instead of use original tf-idf score algorithm.


## How to install this plugin to elasticsearch?

* git clone https://github.com/jasstionzyf/payload-plugin.git
* cd ./payload-plugin
* mvn clean install
* find payload-plugin-1.0-SNAPSHOT.jar
* create payloadPlugin under your ES_HOME/plugins/
* move plugin-descriptor.properties and payload-plugin-1.0-SNAPSHOT.jar to the folder and restart elasticsearch



## How to use this plugin?

* Create right settings and mapping fields.
  
  you can check src/test/example/image_test.json.
  
  easily view it, you can use https://jsonformatter.curiousconcept.com/.
  
  this example config will set kwPayloads as payload field.
  
  you can update the index name to your own, add more other fields.
  
  


* Use following query to apply payload plugin
  
  you can specify multi values to the payload field and combine their scores to finally sort query result.
  
  you can check src/test/example/query.json
  
   
   
   

## How to update payload field content?
   Use elasticsearch _update_by_query.
   you can check src/test/example/update.json.
    




### More details please check my csdn blog.

### Any questions or suggestions you can sumbit a issue, I will check them as soon as i can.
