package ka.thriftoverhttpusingspringboot;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.LoggerFactory;

import ka.thriftoverhttpusingspringboot.api.ArithematicException;
import ka.thriftoverhttpusingspringboot.api.CalculationService;
import ka.thriftoverhttpusingspringboot.api.Operation;

public class CalculatorClient {
  private static int port = 8080;
  private static String host = "localhost";

  static org.slf4j.Logger logger = LoggerFactory.getLogger(CalculatorClient.class);

  public static void main(String[] args) {
    try {
      THttpClient httpClient = new THttpClient("http://" + host + ":" + port + "/calculator");
      httpClient.setConnectTimeout(3000);

      TProtocol protocol = new TJSONProtocol(httpClient);
      CalculationService.Client client = new CalculationService.Client(protocol);

      int x = 300;
      int y = 100;

      logger.debug("{}x{}={}", x, y, client.multiply(x, y));
      logger.debug("{}+{}={}", x, y, client.add(x, y));
      logger.debug("{}/{}={}", x, y, client.divide(x, y));
      logger.debug("{}-{}={}", x, y, client.subtract(x, y));
      logger.debug("{}x{}={}", x, y, client.operate(Operation.MULTIPLY, x, y));
      logger.debug("{}+{}={}", x, y, client.operate(Operation.ADD, x, y));
      logger.debug("{}/{}={}", x, y, client.operate(Operation.DIVIDE, x, y));
      logger.debug("{}-{}={}", x, y, client.operate(Operation.SUBTRACT, x, y));
      try {
        client.operate(Operation.DIVIDE, x, 0);
      } catch (ArithematicException exception) {
        logger.debug("ArithematicException Message:\"{}\" Param1:\"{}\" Param2:\"{}\"",
            exception.getMessage(), exception.getParam1(), exception.getParam2());
      }
      httpClient.close();
    } catch (TTransportException e) {
      logger.debug("Connection failed : {}", e.getMessage());
    } catch (TException x) {
      logger.debug("Api exception : {}", x.getMessage());
    }
  }

}
