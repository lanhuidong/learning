package com.nexusy.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JMongo {

    private static AtomicInteger finished = new AtomicInteger();
    private static int deviceNum = 10000;
    private static AtomicInteger eventId = new AtomicInteger();
    private static String[] deviceIds = new String[deviceNum];

    static {
        for (int i = 0; i < deviceNum; i++) {
            deviceIds[i] = String.valueOf(1000000 + i);
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
        int recordNum = Integer.valueOf(args[1]);
        int threadNum = Integer.valueOf(args[2]);
        switch (args[0]) {
            case "w1":
                write1(recordNum, threadNum);
                break;
            case "q1":
                query1(recordNum, threadNum);
                break;
        }
    }

    public static void write1(int recodeNum, int threadNum) {
        final int total = recodeNum / threadNum;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        CompletionService<Void> service = new ExecutorCompletionService<>(executor);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.31.0.187:27018/?maxPoolSize=20"));
        MongoDatabase database = mongoClient.getDatabase("test");
        final MongoCollection<Document> collection = database.getCollection("events");
        //创建两个复合索引
        BasicDBObject index1 = new BasicDBObject();
        index1.put("device_id", 1);
        index1.put("event_index", 1);
        collection.createIndex(index1);
        BasicDBObject index2 = new BasicDBObject();
        index2.put("device_id", 1);
        index2.put("device_time", 1);
        collection.createIndex(index2);

        long startTime = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            service.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    long t1 = System.nanoTime();
                    int i = 0;
                    long t3 = t1;
                    long t4;
                    while (i < total) {
                        collection.insertOne(buildEvents(i));
                        i++;
                        if (i % 100000 == 0) {
                            int tmp = finished.addAndGet(100000);
                            t4 = System.nanoTime();
                            System.out.println((t4 - t3) + "已插入" + i + "条, 共" + tmp + "条");
                            t3 = t4;
                        }
                    }
                    long t2 = System.nanoTime();
                    System.out.println(Thread.currentThread().getName() + " time: " + (t2 - t1) + "ns");
                    return null;
                }
            });
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                service.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        executor.shutdown();
        System.out.println("total time: " + (endTime - startTime) + "ns");
    }

    public static void query1(int recodeNum, int threadNum) {
        final int total = recodeNum / threadNum;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        CompletionService<Void> service = new ExecutorCompletionService<>(executor);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27018/?maxPoolSize=20"));
        MongoDatabase database = mongoClient.getDatabase("test");
        final MongoCollection<Document> collection = database.getCollection("events");
        long startTime = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            service.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    long t1 = System.nanoTime();
                    int i = 0;
                    long t3 = t1;
                    long t4;
                    while (i < total) {
                        BasicDBObject filter = new BasicDBObject();
                        int idx = (int) (Math.random() * 10000);
                        filter.put("device_id", deviceIds[idx]);
                        FindIterable<Document> result = collection.find(filter).limit(1);
                        result.first();
                        i++;
                        if (i % 100000 == 0) {
                            int tmp = finished.addAndGet(100000);
                            t4 = System.nanoTime();
                            System.out.println((t4 - t3) + "已插入" + i + "条, 共" + tmp + "条");
                            t3 = t4;
                        }
                    }
                    long t2 = System.nanoTime();
                    System.out.println(Thread.currentThread().getName() + " time: " + (t2 - t1) + "ns");
                    return null;
                }
            });
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                service.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        executor.shutdown();
        System.out.println("total time: " + (endTime - startTime) + "ns");
    }

    private static Document buildEvents(int i) {
        Document doc = new Document();
        doc.put("action", "start");
        doc.put("chn", 0);
        doc.put("device_id", deviceIds[i % deviceNum]);
        int time = (int) (System.nanoTime());
        if (time < 0) {
            time = -time;
        }
        doc.put("device_time", time);

        int id = eventId.getAndIncrement();
        Document data = new Document();
        data.put("EventID", String.valueOf(id));
        data.put("Name", null);
        data.put("SnapCount", null);
        data.put("UTC", time);
        doc.put("event_data", data);

        doc.put("event_id", id);
        doc.put("event_index", time + "-" + id);
        doc.put("event_code", "AAA");
        doc.put("event_name", "");
        doc.put("from", "0.V.1007421@");
        doc.put("jpg_num", 0);
        doc.put("server_time", time);
        doc.put("video_num", 0);
        return doc;
    }

}