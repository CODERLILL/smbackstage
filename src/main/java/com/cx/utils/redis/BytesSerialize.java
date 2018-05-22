package com.cx.utils.redis;

import org.apache.commons.lang3.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class BytesSerialize implements Serialize {

    public <O, I> O serialize(I obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] bytes = baos.toByteArray();
            return (O) bytes;
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    public <O, I> O unSerialize(I bytes) {
        // 避免空指针
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream((byte[]) bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (O) ois.readObject();
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

}
