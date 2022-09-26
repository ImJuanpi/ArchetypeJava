package mx.com.axity.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.axity.commons.to.ComputerOrderTO;
import mx.com.axity.commons.to.MonitorTO;
import mx.com.axity.commons.to.OrdersTO;
import mx.com.axity.services.facade.IComputerFacade;
import mx.com.axity.services.facade.IOrdersFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("serviceorder/v1")
@Api(value="ControlOrders")
@ConfigurationProperties(prefix = "")
public class HelloController {

    static final Logger LOG = LogManager.getLogger(HelloController.class);

    //@Autowired
    //RestTemplate restTemplate;

    @Autowired
    IOrdersFacade IordersFacade;

    @Autowired
    IComputerFacade IcomputerFacade;

    @GetMapping(value = "/orders/{id}", produces = "application/json")
    @ApiOperation(value = "Get Order by id",
            notes = "Return Order by id",
            response = OrdersTO.class,
            produces = "application/json")
    public ResponseEntity<OrdersTO> getOrderById(@PathVariable("id") int id) {
        OrdersTO orders = this.IordersFacade.getOrdersById(id);
        if (orders != null){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(orders, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create order by order",
            notes = "Return CREATED",
            response = OrdersTO.class,
            produces = "application/json")
    public @ResponseBody ResponseEntity createOrder(@RequestBody OrdersTO NewOrder){
        if(NewOrder.getComputers().size() > 5){
            return new ResponseEntity<>("Error, more than 5 Computers", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            return new ResponseEntity(IordersFacade.createOrder(NewOrder), HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "/computer", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create order by order",
            notes = "Return CREATED",
            response = ComputerOrderTO.class,
            produces = "application/json")
    public @ResponseBody ResponseEntity createComputerOrder(@RequestBody ComputerOrderTO NewOrder){
        return new ResponseEntity (IcomputerFacade.createComputerOrder(NewOrder), HttpStatus.CREATED);
    }

    @GetMapping(value = "/ping", produces = "application/json")
    @ApiOperation(value = "Ping",
            notes = "Pong",
            produces = "application/json")
    public ResponseEntity test() {
        return new ResponseEntity<>("{pong}", HttpStatus.OK);
    }
}
