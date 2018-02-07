# 获取byte数组的实际使用长度

背景：byte.length只能获取到初始化的byte数组长度，而不是实际使用的长度，因此想要获取到实际的使用长度只能靠其他方法实现。

## 遍历方式获取
```
byte[] data = new byte[2048];
int i = 0; 
for (; i < data.length; i++) { 
    if (data[i] == '\0') 
        break; 
} 
return i; 
```

## 使用Vector
使用Vector，将byte数组放到里面，然后在获取长度。
```
vector.addElement(byte[]);
vector.size();
vecotr.removeElementAt(0);
vector.size();
```