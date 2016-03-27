
## ask


### execute

./activator "run-main com.example.helloworld.ask.Main"

### explain

"ask" is wait the result as Future[Any]

actor is response the result as "tell" to the sender.

## log

### execute

./activator "run-main com.example.sample.multiLog.Main"

### explain

## pingpong


### execute

./activator "run-main com.example.helloworld.pingpong.Main"

### explain

ParentActor will create ChildActor and send message,
and wait the response as other process

**WARNING**

if ParentActor wait as Future[Any], it may break sequential processing.
(Promise's process & new message process will conflict.)


## router


### execute

./activator "run-main com.example.helloworld.router.Main"

### explain

router create new Actor, and send message to these Actor.

## tell

### execute

./activator "run-main com.example.helloworld.tell.Main"

### explain

you can use "!" instead of "tell", 
! is send message from `self` object

## sequential

### execute

./activator "run-main com.example.sample.sequential.Main"

### explain

you can see tasks will be executed as sequential

## parallel

### execute

./activator "run-main com.example.sample.parallel.Main"

### explain

you can see tasks will be executed as parallel