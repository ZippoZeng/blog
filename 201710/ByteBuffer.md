# ByteBuffer

## 缓冲区
缓冲区(Buffer)就是在内存中预留指定大小的存储空间用来对输入/输出(I/O)的数据作临时存储，这部分预留的内存空间就叫做缓冲区：

使用缓冲区有这么两个好处：
1、减少实际的物理读写次数
2、缓冲区在创建时就被分配内存，这块内存区域一直被重用，可以减少动态分配和回收内存的次数

在Java NIO中，缓冲区的作用是用来临时存储数据，可以理解为是I/O操作钟数据的中转站。缓冲区直接为通道(Channel)服务，写入数据到通道或从通道读取数据，这样的操利用缓冲区数据来传递就可以达到对数据高效处理的目的。在NIO中主要有八种缓冲区类：

ByteBuffer-->MappedByteBuffer(用于内存映射的一种ByteBuffer)
CharBuffer
ShortBuffer
IntBuffer
LongBuffer
FloatBuffer
DoubleBuffer


## Fields
所有缓冲区都有4个属性：capacity、limit、position、mark，并遵循：mark <= position <= limit <= capacity，下表格是对着4个属性的解释：
| 属性      | 描述    | 
| --------  |  :----|
| Capacity  | 容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变  |
| Limit     | 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改de |
| Position  | 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备    |
| Mark      | 标记，调用mark()来设置mark=position，再调用reset()可以让position恢复到标记的位置|

## Methods

### 实例化
java.nio.Buffer类是一个抽象类，不能被实例化。Buffer类的直接子类，如ByteBuffer等也是抽象类，所以也不能被实例化。
但是ByteBuffer类提供了4个静态工厂方法来获得ByteBuffer的实例：
| 方法      	| 描述	| 
| --------	| :----	|
| allocate(int capacity) | 从堆空间中分配一个容量大小为capacity的byte数组作为缓冲区的byte数据存储器 |
| allocateDirect(int capacity) | 是不使用JVM堆栈而是通过操作系统来创建内存块用作缓冲区，它与当前操作系统能够更好的耦合，因此能进一步提高I/O操作速度。但是分配直接缓冲区的系统开销很大，因此只有在缓冲区较大并长期存在，或者需要经常重用时，才使用这种缓冲区 |
| wrap(byte[] array)	 | 这个缓冲区的数据会存放在byte数组中，bytes数组或buff缓冲区任何一方中数据的改动都会影响另一方。其实ByteBuffer底层本来就有一个bytes数组负责来保存buffer缓冲区中的数据，通过allocate方法系统会帮你构造一个byte数组 |
| wrap(byte[] array,int offset, int length) | 在上一个方法的基础上可以指定偏移量和长度，这个offset也就是包装后byteBuffer的position，而length呢就是limit-position的大小，从而我们可以得到limit的位置为length+position(offset) |

### 另外一些常用的方法 
| 方法      | 描述    | 
| --------  |  :----|
| limit(), limit(10)等 | 其中读取和设置这4个属性的方法的命名和jQuery中的val(),val(10)类似，一个负责get，一个负责set |
| reset() |	把position设置成mark的值，相当于之前做过一个标记，现在要退回到之前标记的地方 |
| clear() |	position = 0;limit = capacity;mark = -1;  有点初始化的味道，但是并不影响底层byte数组的内容 |
| flip() | limit = position;position = 0;mark = -1;  翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态 |
| rewind() | 把position设为0，mark设为-1，不改变limit的值 |
| remaining() | return limit - position; 返回limit和position之间相对位置差 | 
| hasRemaining() | return position < limit返回是否还有未读内容 |
| compact()	| 把从position到limit中的内容移到0到limit-position的区域内，position和limit的取值也分别变成limit-position、capacity。如果先将positon设置到limit，再compact，那么相当于clear() |
| get()	| 相对读，从position位置读取一个byte，并将position+1，为下次读写作准备 |
| get(int index) | 绝对读，读取byteBuffer底层的bytes中下标为index的byte，不改变position |
| get(byte[] dst, int offset, int length) | 从position位置开始相对读，读length个byte，并写入dst下标从offset到offset+length的区域|
| put(byte b) | 相对写，向position的位置写入一个byte，并将postion+1，为下次读写作准备 |
| put(int index, byte b) | 绝对写，向byteBuffer底层的bytes中下标为index的位置插入byte b，不改变position |
| put(ByteBuffer src) | 用相对写，把src中可读的部分（也就是position到limit）写入此byteBuffer |
| put(byte[] src, int offset, int length) | 从src数组中的offset到offset+length区域读取数据并使用相对写写入此byteBuffer |
