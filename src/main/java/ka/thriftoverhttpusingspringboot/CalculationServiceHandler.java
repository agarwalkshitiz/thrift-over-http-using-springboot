package ka.thriftoverhttpusingspringboot;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import ka.thriftoverhttpusingspringboot.api.CalculationService;

@Component
public class CalculationServiceHandler implements CalculationService.Iface {

  @Override
  public int multiply(int n1, int n2) throws TException {
    return n1 * n2;
  }

  @Override
  public int add(int n1, int n2) throws TException {
    return n1 + n2;
  }

  @Override
  public int subtract(int n1, int n2) throws TException {
    return n1 - n2;
  }

  @Override
  public int divide(int n1, int n2) throws TException {
    return n1 / n2;
  }

}
