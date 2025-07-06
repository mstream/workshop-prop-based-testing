package workshop.counter;

class ThreadSafeCounterImpl extends ThreadUnsafeCounterImpl {

    @Override
    public synchronized void increment(String name) {
        super.increment(name);
    }

    @Override
    public String toString() {
        return "ThreadSafeCounter";
    }
}
