<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<#macro p value>${value}</#macro>

<mapper namespace="mapper.${tableNameUpperFirst}Mapper">

	<sql id="query_where">
		<#list fields as field>
		<if test="${field.changedName} != null">
			and ${field.name} like CONCAT('%',<@p value="#{"/>${field.changedName}},'%')
		</if>
		</#list>
	</sql>

	<insert id="insertOne" parameterType="${tableNameUpperFirst}">
		INSERT INTO ${tableName}(
			<#list fields as field>
			${field.name}<#sep>,
			</#list>
		) VALUES (
			<#list fields as field>
			<@p value="#{"/>${field.changedName}}<#sep>,
			</#list>
		)
	</insert>

	<insert id="insertBatch" parameterType="List">
		INSERT INTO ${tableName}(
			<#list fields as field>
			${field.name}<#sep>,
			</#list>
		) VALUES
		<foreach collection="list" item="item" index="index"
			separator=",">
			(<#list fields as field>
			<@p value="#{"/>item.${field.changedName}}<#sep>,
			</#list>)
		</foreach>
	</insert>

	<delete id="deleteOne" parameterType="int">
		DELETE FROM ${tableName}
		WHERE
		id=<@p value="#{"/>id}
	</delete>

	<delete id="deleteBatch" parameterType="List">
		DELETE FROM ${tableName}
		WHERE id IN
		<foreach collection="list" item="item" open="(" close=")"
			separator=",">
			<@p value="#{"/>item}
		</foreach>
	</delete>

	<update id="updateOne" parameterType="${tableNameUpperFirst}">
		UPDATE ${tableName}
		<set>
			<#list fields as field>
			${field.name} = <@p value="#{"/>${field.changedName}}<#sep>,
			</#list>
		</set>
		WHERE id=<@p value="#{"/>id}
	</update>

	<update id="updateBatch" parameterType="List">
		<foreach collection="list" item="item" open="" close="" separator=";">
		UPDATE ${tableName}
		<set>
			<#list fields as field>
			${field.name} = <@p value="#{"/>item.${field.changedName}}<#sep>,
			</#list>
		</set>
		WHERE id=<@p value="#{"/>item.id}
		</foreach>
	</update>

	<select id="selectByCondition" parameterType="${tableNameUpperFirst}" resultType="${tableNameUpperFirst}">
		SELECT * FROM ${tableName}
		<where>
			<include refid="query_where"></include>
		</where>
	</select>

	<select id="selectByPage" parameterType="${tableNameUpperFirst}" resultType="${tableNameUpperFirst}">
		SELECT * FROM ${tableName}
		<where>
			<include refid="query_where"></include>
		</where>
		LIMIT <@p value="#{"/>startRow},<@p value="#{"/>rows}
	</select>

</mapper>