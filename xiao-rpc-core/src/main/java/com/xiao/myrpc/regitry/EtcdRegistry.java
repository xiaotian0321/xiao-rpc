package com.xiao.myrpc.regitry;

import com.xiao.myrpc.config.RegistryConfig;
import com.xiao.myrpc.model.ServiceMetaInfo;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.api.Kv;
import io.etcd.jetcd.kv.GetResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author tian.xiao
 * @title EtcdRegistry
 * @create 2025/8/8 12:05
 */

public class EtcdRegistry implements Registry{

    private Client client;

    private Kv kv;
    @Override
    public void init(RegistryConfig registryConfig) {

    }

    @Override
    public void register(ServiceMetaInfo serviceMetaInfo) throws Exception {

    }

    @Override
    public void unRegister(ServiceMetaInfo serviceMetaInfo) {

    }

    @Override
    public List<ServiceMetaInfo> serviceDiscovery(String serviceKey) {
        return null;
    }

    @Override
    public void destroy() {

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = Client.builder().endpoints("http://172.20.90.99:2379").build();

        KV kvClient = client.getKVClient();
        ByteSequence key = ByteSequence.from("test".getBytes());
        ByteSequence value = ByteSequence.from("123465".getBytes());

        kvClient.put(key, value);

        CompletableFuture<GetResponse> responseCompletableFuture = kvClient.get(key);

        GetResponse response = responseCompletableFuture.get();

        // delete the key
        kvClient.delete(key).get();
    }
}
