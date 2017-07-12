# WebserviceProtocal

WebService
---
WebService的数据传输格式是基于XML文档规范的，数据信息的传输就是以XML的形式来完成的。由于XML不受平台和语言的限制，所以WebService可以实现远程调用，调用服务的语言可以是任意的。  

SOAP协议
--
  SOAP协议被称之为**简单对象访问协议**，它的作用就是用来描述信息的传输格式。一条SIAO消息其实就是一个XML文档，SOAP可以规定一条消息是由谁进行发送的，以及由谁来进行接收和处理，这就属于SOAP的封装，它基于XML的数据格式和Http协议完成XML数据信息的传递  

WSDL
--
  如果说SOAP（简单对象访问协议）是用来完成数据信息的传递，那么WSDL就是规定数据信息以怎样的方式进行传递。WSDL是WebService的描述语言，目的是描述WebService上的每一个函数。  
> 函数调用的前提是：需要知道函数名、函数的功能，以及函数调用需要传递的相关参数和返回值。  
  WSDL就是用来完成这个功能的，它基于XML文档，是一个机器可以解析的标准文档（其实就是描述应用函数的规范）

UDDI
--
  UDDI是WebService的第三大要素，他的功能是完成WebService的注册和搜索。搜索就是在网络上的众多方法中取查找我们需要的那个方法，注册则是生成一个新的函数  

总结
--
  总的来说就是uddi完成调用服务的查找，在获取WSDL之后，我们就可以对其进行调用，调用的过程中，传递的对象为SOAP封装好的对象 

在Android中，要使用WebService方式通信，需要用到一个jar包： 
`ksoap2-android-assembly-x.x.x-jar-with-dependencies`（x.x.x未改包的版本号） 
  本项目采用mvp模式使用WebService通信来简单实现了获取天气查询支持的省份功能
