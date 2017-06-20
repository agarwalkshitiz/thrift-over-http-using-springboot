package ka.thriftoverhttpusingspringboot;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import ka.thriftoverhttpusingspringboot.api.ArithematicException;
import ka.thriftoverhttpusingspringboot.api.CalculationService;
import ka.thriftoverhttpusingspringboot.api.Operation;

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

  @Override
  public int operate(Operation operation, int n1, int n2) throws ArithematicException, TException {
    switch (operation) {
      case ADD:
        return n1 + n2;
      case DIVIDE:
        if (n2 == 0) {
          throw new ArithematicException("divisor cannot be zero for division operation", n1, n1);
        }
        return n1 / n2;
      case MULTIPLY:
        return n1 * n2;
      case SUBTRACT:
        return n1 - n2;
      default:
        break;
    }
    return 0;
  }
}
