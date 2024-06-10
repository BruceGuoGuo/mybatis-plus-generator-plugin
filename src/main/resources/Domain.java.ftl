package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Desc: ${table.comment}
 */
@Component
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}>{

}
