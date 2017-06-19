namespace java ka.thriftoverhttpusingspringboot.api

typedef i32 int // We can use typedef to get pretty names for the types we are using
service CalculationService
{
        int multiply(1:int n1, 2:int n2),
        int add(1:int n1, 2:int n2),
        int subtract(1:int n1, 2:int n2),
        int divide(1:int n1, 2:int n2),
}