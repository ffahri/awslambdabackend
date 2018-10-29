import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Person;

import java.util.Iterator;
import java.util.List;

public class LambdaMain implements RequestHandler<String,List<Person>> {

    public List<Person> handleRequest(String s, Context context) {

        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
        final DynamoDBMapper mapper = new DynamoDBMapper(ddb);
        List<Person> personList = null;
        final DynamoDB dynamoDB = new DynamoDB(ddb);
        try {
            personList = mapper.scan(Person.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);

        }
        return personList;
    }

}


