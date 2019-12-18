# 使用protobuf 实现网络数据序列化
1. Android端protobuf
2. 服务器端protobuf


### Android端protobuf
1. Android跟目录gradle添加插件
2. Android项目目录gradle下添加protobuf引用
3. 编写Login.proto文件，编译会在build路径下生成对应的类
4. 调用

### 服务器端protobuf
1. 拷贝Android端生成的proto 类
2. 添加CommonConfig解析