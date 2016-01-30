
## execute

./activator "run-main com.example.helloworld.pingpong.Main"

## explain

ParentActor will create ChildActor and send message,
and wait the response as other process

**WARNING**

if ParentActor wait as Future[Any], it may break sequential processing.
(Promise's process & new message process will conflict.)