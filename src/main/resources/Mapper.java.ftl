package ${package.Mapper};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ${author}
 * @Date: ${date}
 * @Desc: ${table.comment}
 */
@Mapper
public interface ${table.mapperName} extends BaseMapper<${entity}>{

}
